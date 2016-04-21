package coo.core.hibernate.search;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.base.util.Assert;
import coo.base.util.BeanUtils;
import coo.base.util.CollectionUtils;
import coo.base.util.StringUtils;

/**
 * Hibernate Search全文搜索查询条件构造器。
 */
public class FullTextCriteria {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private FullTextSession session;
	private Class<?> clazz;
	/** 搜索关键字 */
	private String keyword;
	/** 待搜索的字段 */
	private Map<String, Analyze> searchFields = new LinkedHashMap<String, Analyze>();
	/** 排序字段 */
	private List<SortField> sortFields = new ArrayList<SortField>();
	/** 附加的Lucene查询条件列表 */
	private List<AttachLuceneQuery> luceneQueries = new ArrayList<AttachLuceneQuery>();
	private Filter filter;
	private Criteria criteriaQuery;
	/** 是否优先从二级缓存中获取数据 */
	private Boolean lookupCache = true;

	/**
	 * 构造方法。
	 * 
	 * @param session
	 *            Hibernate全文搜索Session
	 * @param clazz
	 *            实体class
	 * @param searchFields
	 *            待搜索的字段
	 */
	public FullTextCriteria(FullTextSession session, Class<?> clazz,
			Map<String, Analyze> searchFields) {
		this.session = session;
		this.clazz = clazz;
		if (CollectionUtils.isNotEmpty(searchFields)) {
			this.searchFields.putAll(searchFields);
		}
	}

	/**
	 * 设置全文搜索关键字。
	 * 
	 * @param keyword
	 *            关键字
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 清空全文搜索字段。
	 */
	public void clearSearchFields() {
		searchFields.clear();
	}

	/**
	 * 添加全文搜索字段。
	 * 
	 * @param fieldNames
	 *            字段名称
	 */
	public void addSearchField(String... fieldNames) {
		for (String fieldName : fieldNames) {
			Field field = BeanUtils.findField(clazz, fieldName);
			Analyze analyze = field.getAnnotation(
					org.hibernate.search.annotations.Field.class).analyze();
			searchFields.put(fieldName, analyze);
		}
	}

	/**
	 * 添加全文搜索字段。
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param analyze
	 *            是否分词
	 */
	public void addSearchField(String fieldName, Analyze analyze) {
		searchFields.put(fieldName, analyze);
	}

	/**
	 * 移除全文搜索字段。
	 * 
	 * @param fieldNames
	 *            字段名称
	 */
	public void removeSearchField(String... fieldNames) {
		for (String fieldName : fieldNames) {
			searchFields.remove(fieldName);
		}
	}

	/**
	 * 增加简单的字段过滤条件。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 */
	public void addFilterField(String fieldName, Object fieldValue) {
		TermQuery query = new TermQuery(new Term(fieldName,
				fieldValue.toString()));
		addLuceneQuery(query, Occur.MUST);
	}

	/**
	 * 增加简单的字段过滤条件（多个字段值）。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValues
	 *            字段值
	 */
	public void addFilterField(String fieldName, List<?> fieldValues) {
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		for (Object fieldValue : fieldValues) {
			TermQuery query = new TermQuery(new Term(fieldName,
					fieldValue.toString()));
			builder.add(query, Occur.SHOULD);
		}
		addLuceneQuery(builder.build(), Occur.MUST);
	}

	/**
	 * 增加简单的字段过滤条件（多个字段值）。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param fieldValues
	 *            字段值
	 */
	public void addFilterField(String fieldName, Object[] fieldValues) {
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		for (Object fieldValue : fieldValues) {
			TermQuery query = new TermQuery(new Term(fieldName,
					fieldValue.toString()));
			builder.add(query, Occur.SHOULD);
		}
		addLuceneQuery(builder.build(), Occur.MUST);
	}

	/**
	 * 增加区间字段查询条件。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param lowerTerm
	 *            最小值
	 * @param upperTerm
	 *            最大值
	 */
	public void addRangeField(String fieldName, String lowerTerm,
			String upperTerm) {
		TermRangeQuery query = TermRangeQuery.newStringRange(fieldName,
				lowerTerm, upperTerm, true, false);
		addLuceneQuery(query, Occur.MUST);
	}

	/**
	 * 增加Lucene查询条件。
	 * 
	 * @param query
	 *            Lucene查询条件
	 * @param occur
	 *            Lucene查询条件关系
	 */
	public void addLuceneQuery(Query query, Occur occur) {
		if (query != null && occur != null) {
			luceneQueries.add(new AttachLuceneQuery(query, occur));
		}
	}

	/**
	 * 增加顺序排列的排序字段。<br/>
	 * 类型请参考SortField的公共常量。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param type
	 *            类型
	 */
	public void addSortAsc(String fieldName, SortField.Type type) {
		addSortAsc(fieldName, type, null);
	}

	/**
	 * 增加顺序排列的排序字段。<br/>
	 * 类型请参考SortField的公共常量。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param type
	 *            类型
	 * @param missingValue
	 *            为空设定值
	 */
	public void addSortAsc(String fieldName, SortField.Type type,
			Object missingValue) {
		SortField sortField = new SortField(fieldName, type);
		if (missingValue != null) {
			sortField.setMissingValue(missingValue);
		}
		sortFields.add(sortField);
	}

	/**
	 * 增加倒序排列的排序字段。<br/>
	 * 类型请参考SortField的公共常量。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param type
	 *            类型
	 */
	public void addSortDesc(String fieldName, SortField.Type type) {
		addSortDesc(fieldName, type, null);
	}

	/**
	 * 增加倒序排列的排序字段。<br/>
	 * 类型请参考SortField的公共常量。
	 * 
	 * @param fieldName
	 *            字段名
	 * @param type
	 *            类型
	 * @param missingValue
	 *            为空设定值
	 */
	public void addSortDesc(String fieldName, SortField.Type type,
			Object missingValue) {
		SortField sortField = new SortField(fieldName, type, true);
		if (missingValue != null) {
			sortField.setMissingValue(missingValue);
		}
		sortFields.add(sortField);
	}

	/**
	 * 设置Lucene的Filter过滤器。
	 * 
	 * @param filter
	 *            Lucene的Filter过滤器
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	/**
	 * 设置Hibernate的Criteria查询条件。<br/>
	 * 该方法应谨慎使用，Criteria查询条件只作用于全文搜索出来的结果集上，不会改变全文搜索的搜索结果。<br/>
	 * 这将可能引起全文搜索的总记录条数与经Criteria查询条件过滤后的结果集的总记录数不一致。
	 * 
	 * @param criteria
	 *            Hibernate的Criteria查询条件
	 */
	public void setCriteriaQuery(Criteria criteria) {
		this.criteriaQuery = criteria;
	}

	/**
	 * 生成Hibernate的FullTextQuery全文搜索对象。
	 * 
	 * @return 返回Hibernate的FullTextQuery全文搜索对象。
	 */
	public FullTextQuery generateQuery() {
		FullTextQuery fullTextQuery = session.createFullTextQuery(
				generateLuceneQuery(), clazz);
		if (!sortFields.isEmpty()) {
			fullTextQuery.setSort(new Sort(sortFields
					.toArray(new SortField[] {})));
		}
		if (filter != null) {
			fullTextQuery.setFilter(filter);
		}
		if (criteriaQuery != null) {
			fullTextQuery.setCriteriaQuery(criteriaQuery);
		}
		if (lookupCache) {
			fullTextQuery.initializeObjectsWith(
					ObjectLookupMethod.SECOND_LEVEL_CACHE,
					DatabaseRetrievalMethod.QUERY);
		}
		return fullTextQuery;
	}

	/**
	 * 生成多字段查询对象。
	 * 
	 * @param query
	 *            查询条件
	 * @param fields
	 *            查询字段
	 * @return 返回多字段查询对象。
	 */
	public Query generateMultiFieldQuery(String query,
			Map<String, Analyze> fields) {
		Assert.notEmpty(fields, "必须指定查询的字段。");
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		for (Entry<String, Analyze> field : fields.entrySet()) {
			if (field.getValue() == Analyze.NO) {
				Term term = new Term(field.getKey(), "*" + query + "*");
				WildcardQuery fuzzyQuery = new WildcardQuery(term);
				builder.add(fuzzyQuery, Occur.SHOULD);
			} else {
				QueryParser parser = new QueryParser(field.getKey(), session
						.getSearchFactory().getAnalyzer(clazz));
				parser.setPhraseSlop(0);
				parser.setAutoGeneratePhraseQueries(true);
				try {
					builder.add(parser.parse(query), Occur.SHOULD);
				} catch (ParseException e) {
					throw new HibernateException("生成多字段查询时发生异常", e);
				}
			}
		}
		return builder.build();
	}

	/**
	 * 生成多字段查询对象。
	 * 
	 * @param query
	 *            查询条件
	 * @param fieldNames
	 *            查询字段
	 * @return 返回多字段查询对象。
	 */
	public Query generateMultiFieldQuery(String query, String[] fieldNames) {
		Assert.notEmpty(fieldNames, "必须指定查询的字段。");
		Map<String, Analyze> fields = new LinkedHashMap<String, Analyze>();
		for (String fieldName : fieldNames) {
			fields.put(fieldName, searchFields.get(fieldName));
		}
		return generateMultiFieldQuery(query, fields);
	}

	/**
	 * 根据当前设置生成Lucene查询对象。
	 * 
	 * @return 返回Lucene查询对象。
	 */
	private Query generateLuceneQuery() {
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		// 如果关键字为空，则匹配任意记录
		if (StringUtils.isEmpty(keyword)) {
			builder.add(new WildcardQuery(new Term("id", "*")), Occur.MUST);
		} else {
			// 支持空格分隔多个关键词，之间是“与”的关系
			for (String key : keyword.trim().split(" ")) {
				builder.add(
						generateMultiFieldQuery(QueryParser.escape(key),
								searchFields), Occur.MUST);
			}
		}
		log.debug("全文搜索包含字段：{}", searchFields.keySet());
		for (AttachLuceneQuery attachLuceneQuery : luceneQueries) {
			builder.add(attachLuceneQuery.getQuery(),
					attachLuceneQuery.getOccur());
		}
		BooleanQuery query = builder.build();
		log.debug("全文搜索[{}]查询语句：{}", clazz.getSimpleName(), query);
		return query;
	}

	public Boolean getLookupCache() {
		return lookupCache;
	}

	public void setLookupCache(Boolean lookupCache) {
		this.lookupCache = lookupCache;
	}

	/**
	 * 附加Lucene查询条件。
	 */
	private class AttachLuceneQuery {
		/** Lucene查询条件 */
		private Query query;
		/** Lucene查询条件关系 */
		private Occur occur;

		/**
		 * 构造方法。
		 * 
		 * @param query
		 *            Lucene查询条件
		 * @param occur
		 *            Lucene查询条件关系
		 */
		public AttachLuceneQuery(Query query, Occur occur) {
			this.query = query;
			this.occur = occur;
		}

		public Query getQuery() {
			return query;
		}

		public Occur getOccur() {
			return occur;
		}
	}
}
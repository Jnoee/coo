package coo.core.hibernate.search;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.annotations.Indexed;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import coo.base.exception.UncheckedException;
import coo.core.hibernate.EntityClassBeanFactoryPostProcessor;

/**
 * 全文索引组件。
 */
@Component
public class FullTextIndexer extends EntityClassBeanFactoryPostProcessor {
	private List<Class<?>> indexedEntityClasses = new ArrayList<Class<?>>();

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) {
		super.postProcessBeanFactory(beanFactory);
		for (Class<?> entityClass : entityClasses) {
			if (entityClass.isAnnotationPresent(Indexed.class)) {
				indexedEntityClasses.add(entityClass);
			}
		}
	}

	/**
	 * 同步创建指定实体类的全文索引，如果未指定实体类则创建全部实体类的全文索引。
	 * 
	 * @param entityClasses
	 *            实体类列表
	 */
	public void startAndWait(Class<?>... entityClasses) {
		try {
			createIndexer(entityClasses).startAndWait();
		} catch (Exception e) {
			throw new UncheckedException("重建全文索引时发生异常。", e);
		}
	}

	/**
	 * 异步创建指定实体类的全文索引。
	 * 
	 * @param entityClasses
	 *            实体类列表
	 */
	public void start(Class<?>... entityClasses) {
		try {
			createIndexer(entityClasses).start();
		} catch (Exception e) {
			throw new UncheckedException("重建全文索引时发生异常。", e);
		}
	}

	/**
	 * 创建索引构建组件。
	 * 
	 * @param entityClasses
	 *            业务实体类
	 * @return 返回索引构建组件。
	 */
	private MassIndexer createIndexer(Class<?>... entityClasses) {
		FullTextSession session = Search.getFullTextSession(sessionFactory
				.getCurrentSession());
		return session.createIndexer(entityClasses).typesToIndexInParallel(2)
				.threadsToLoadObjects(20).batchSizeToLoadObjects(25)
				.cacheMode(CacheMode.NORMAL);
	}

	public List<Class<?>> getIndexedEntityClasses() {
		return indexedEntityClasses;
	}
}
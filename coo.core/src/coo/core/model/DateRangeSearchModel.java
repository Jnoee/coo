package coo.core.model;

import java.util.Date;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.joda.time.DateTime;

import coo.base.exception.BusinessException;
import coo.base.util.DateUtils;
import coo.core.model.SearchModel;

/**
 * 日期区间查询条件模型。
 */
public class DateRangeSearchModel extends SearchModel {
	/** 起始日期 */
	private Date startDate;
	/** 截止日期 */
	private Date endDate;

	/**
	 * 生成日期区间全文搜索查询对象。
	 * 
	 * @param searchField
	 *            待查询的全文搜索字段
	 * 
	 * @return 返回日期区间全文搜索查询对象。
	 */
	public Query genQuery(String searchField) {
		if (startDate == null || endDate == null) {
			return null;
		}
		String startDateStr = DateUtils.format(startDate, DateUtils.DAY_N);
		DateTime endDateTime = new DateTime(endDate).plusDays(1);
		String endDateStr = DateUtils.format(endDateTime.toDate(),
				DateUtils.DAY_N);
		TermRangeQuery showTimeQuery = new TermRangeQuery(searchField,
				startDateStr, endDateStr, true, false);
		return showTimeQuery;
	}

	/**
	 * 验证日期区间设置有效性。
	 */
	public void validate() {
		if (startDate == null && endDate == null) {
			return;
		}
		if (startDate != null && endDate != null) {
			if (startDate.after(endDate)) {
				throw new BusinessException("查询起始日期不能大于截止日期。");
			}
		} else {
			throw new BusinessException("查询起始日期和截止日期需要同时指定。");
		}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
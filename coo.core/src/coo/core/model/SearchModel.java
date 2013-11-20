package coo.core.model;

import java.io.Serializable;

/**
 * 用于分页/全文检索的查询条件模型。<br/>
 * 更多条件的查询条件模型可继承该类进行定义。
 */
public class SearchModel implements Serializable {
	/** 当前页码 */
	protected Integer pageNum = 1;
	/** 每页记录数 */
	protected Integer numPerPage = 20;
	/** 排序字段 */
	protected String orderField;
	/** 全文检索关键字 */
	protected String keyText;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getKeyText() {
		return keyText;
	}

	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}
}

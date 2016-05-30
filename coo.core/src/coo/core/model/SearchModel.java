package coo.core.model;

/**
 * 用于分页/全文检索的查询条件模型。<br>
 * 更多条件的查询条件模型可继承该类进行定义。
 */
public class SearchModel {
  /** 当前页码 */
  protected Integer pageNo = 1;
  /** 每页记录数 */
  protected Integer pageSize = 20;
  /** 排序字段 */
  protected String orderBy;
  /** 排序顺序 */
  protected String sort;
  /** 全文检索关键字 */
  protected String keyword;

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}

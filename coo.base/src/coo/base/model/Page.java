package coo.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象。
 * 
 * @param <T> 分页对象中包含内容的对象类型
 */
public class Page<T> implements Serializable {
  private static final long serialVersionUID = 5212026127179748487L;
  /** 是否第一页 */
  private Boolean first = true;
  /** 是否最后一页 */
  private Boolean last = true;
  /** 总页数 */
  private Integer pageCount = 0;
  /** 总记录数 */
  private Integer count = 0;
  /** 下一页页码 */
  private Integer next = 1;
  /** 上一页页码 */
  private Integer previous = 1;
  /** 每页记录数 */
  private Integer size;
  /** 当前页码 */
  private Integer number = 1;
  /** 分页记录集合 */
  private List<T> contents = new ArrayList<>();
  /** 页数序号 */
  private List<Integer> indexs = new ArrayList<>();

  /**
   * 初始化一个新的分页对象，该构造方法通常用于生成一个空的分页对象。
   * 
   * @param pageSize 每页记录数
   */
  public Page(Integer pageSize) {
    size = pageSize;
  }

  /**
   * 通过指定记录总数、当前页数、每页记录数来构造一个分页对象。
   * 
   * 
   * @param recordCount 记录总数
   * @param pageNo 当前页数
   * @param pageSize 每页记录数
   */
  public Page(Integer recordCount, Integer pageNo, Integer pageSize) {
    count = recordCount;
    size = pageSize;
    pageCount = count % size > 0 ? count / size + 1 : count / size;
    number = pageCount < pageNo ? pageCount : pageNo;
    first = number <= 1 ? true : false;
    previous = number <= 1 ? number : number - 1;
    last = number >= pageCount ? true : false;
    next = number >= pageCount ? number : number + 1;
    contents = new ArrayList<>();
    indexs = new ArrayList<>();
    for (int i = 1; i <= pageCount; i++) {
      indexs.add(i);
    }
  }

  public Boolean getFirst() {
    return first;
  }

  public void setFirst(Boolean first) {
    this.first = first;
  }

  public Boolean getLast() {
    return last;
  }

  public void setLast(Boolean last) {
    this.last = last;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public Integer getNext() {
    return next;
  }

  public void setNext(Integer next) {
    this.next = next;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getPrevious() {
    return previous;
  }

  public void setPrevious(Integer previous) {
    this.previous = previous;
  }

  public List<T> getContents() {
    return contents;
  }

  public void setContents(List<T> contents) {
    this.contents = contents;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<Integer> getIndexs() {
    return indexs;
  }

  public void setIndexs(List<Integer> indexs) {
    this.indexs = indexs;
  }
}

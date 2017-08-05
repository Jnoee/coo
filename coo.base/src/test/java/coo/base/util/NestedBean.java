package coo.base.util;

public class NestedBean {
  private String name;
  private NestedBean nestedBean;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NestedBean getNestedBean() {
    return nestedBean;
  }

  public void setNestedBean(NestedBean nestedBean) {
    this.nestedBean = nestedBean;
  }
}

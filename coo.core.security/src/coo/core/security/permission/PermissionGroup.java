package coo.core.security.permission;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 权限分组。对应权限配置文件中的permission-group节点。
 */
@XStreamAlias("permission-group")
public class PermissionGroup implements Comparable<PermissionGroup> {
  @XStreamAsAttribute
  private String name;
  @XStreamAsAttribute
  private Integer order;
  @XStreamImplicit
  private List<Permission> permissions = new ArrayList<Permission>();

  @Override
  public int compareTo(PermissionGroup other) {
    return order - other.getOrder();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  /**
   * 获取权限列表。
   * 
   * @return 返回权限列表。
   */
  public List<Permission> getPermissions() {
    // xstream不会调用类的默认构造方法来构造对象
    // 所以在get方法中确保permissions不为null以方便后续处理
    if (permissions == null) {
      permissions = new ArrayList<Permission>();
    }
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }
}

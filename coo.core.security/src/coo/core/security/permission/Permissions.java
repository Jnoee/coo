package coo.core.security.permission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 权限配置。对应权限配置文件中的permissions节点。
 */
@XStreamAlias("permissions")
public class Permissions {
  @XStreamImplicit
  private List<PermissionGroup> permissionGroups = new ArrayList<PermissionGroup>();

  /**
   * 合并权限配置。
   * 
   * @param permissions 待合并的权限配置
   */
  public void merge(Permissions permissions) {
    for (PermissionGroup permissionGroup : permissions.getPermissionGroups()) {
      PermissionGroup existPermissionGroup = getPermissionGroup(permissionGroup.getName());
      if (existPermissionGroup == null) {
        permissionGroups.add(permissionGroup);
        Collections.sort(permissionGroup.getPermissions());
      } else {
        existPermissionGroup.getPermissions().addAll(permissionGroup.getPermissions());
        Collections.sort(existPermissionGroup.getPermissions());
      }
    }
    Collections.sort(permissionGroups);
  }

  /**
   * 根据权限组名称获取权限组。
   * 
   * @param permissionGroupName 权限组名称
   * @return 返回对应名称的权限组，如果没有找到则返回空。
   */
  private PermissionGroup getPermissionGroup(String permissionGroupName) {
    for (PermissionGroup permissionGroup : permissionGroups) {
      if (permissionGroup.getName().equals(permissionGroupName)) {
        return permissionGroup;
      }
    }
    return null;
  }

  /**
   * 获取权限分组列表。
   * 
   * @return 返回权限分组列表。
   */
  public List<PermissionGroup> getPermissionGroups() {
    // xstream不会调用类的默认构造方法来构造对象
    // 所以在get方法中确保permissionGroups不为null以方便后续处理
    if (permissionGroups == null) {
      permissionGroups = new ArrayList<PermissionGroup>();
    }
    return permissionGroups;
  }

  public void setPermissionGroups(List<PermissionGroup> permissionGroups) {
    this.permissionGroups = permissionGroups;
  }
}

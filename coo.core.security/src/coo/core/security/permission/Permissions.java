package coo.core.security.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 权限配置。对应权限配置文件中的permissions节点。
 */
@XStreamAlias("permissions")
public class Permissions implements Serializable {
	@XStreamImplicit
	private List<PermissionGroup> permissionGroups = new ArrayList<PermissionGroup>();

	public List<PermissionGroup> getPermissionGroups() {
		return permissionGroups;
	}

	public void setPermissionGroups(List<PermissionGroup> permissionGroups) {
		this.permissionGroups = permissionGroups;
	}
}

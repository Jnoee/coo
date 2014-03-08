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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}

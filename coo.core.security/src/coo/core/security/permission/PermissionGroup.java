package coo.core.security.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 权限分组。对应权限配置文件中的permission-group节点。
 */
@XStreamAlias("permission-group")
public class PermissionGroup implements Serializable {
	private static final long serialVersionUID = -645054455683550775L;
	@XStreamAsAttribute
	private String name;
	@XStreamImplicit
	private List<Permission> permissions = new ArrayList<Permission>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}

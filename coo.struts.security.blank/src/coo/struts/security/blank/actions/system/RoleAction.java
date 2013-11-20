package coo.struts.security.blank.actions.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.core.security.permission.PermissionConfig;
import coo.core.security.permission.PermissionGroup;
import coo.struts.actions.GenericAction;
import coo.struts.security.blank.entity.Role;
import coo.struts.security.blank.service.SecurityService;
import coo.struts.util.AjaxResultUtils;

/**
 * 角色管理。
 */
@Auth(AdminPermission.CODE)
public class RoleAction extends GenericAction {
	@Resource
	private SecurityService securityService;
	@Resource
	private PermissionConfig permissionConfig;
	private List<Role> roles = new ArrayList<Role>();
	private Role role;
	private List<PermissionGroup> permissionGroups = new ArrayList<PermissionGroup>();
	private List<Integer> permissionIds = new ArrayList<Integer>();

	/**
	 * 查看角色列表。
	 * 
	 * @return 返回查看角色列表页面。
	 */
	@Action("role-list")
	public String list() {
		roles = securityService.getAllRole();
		return SUCCESS;
	}

	/**
	 * 新增角色。
	 * 
	 * @return 返回新增角色页面。
	 */
	@Action("role-add")
	public String add() {
		role = new Role();
		permissionGroups = permissionConfig.getPermissionGroups();
		return SUCCESS;
	}

	/**
	 * 保存角色。
	 * 
	 * @return 返回保存角色成功提示。
	 */
	@Action("role-save")
	public String save() {
		role.setPermissions(permissionConfig.getPermissionCode(permissionIds));
		securityService.createRole(role);
		return AjaxResultUtils.close(getMessage("role.add.success"),
				"role-list");
	}

	/**
	 * 编辑角色。
	 * 
	 * @return 返回编辑角色页面。
	 */
	@Action("role-edit")
	public String edit() {
		String roleId = request.getParameter("roleId");
		role = securityService.getRole(roleId);
		permissionGroups = permissionConfig.getPermissionGroups();
		permissionIds = permissionConfig
				.getPermissionIds(role.getPermissions());
		return SUCCESS;
	}

	/**
	 * 更新角色。
	 * 
	 * @return 返回更新角色成功提示。
	 */
	@Action("role-update")
	public String update() {
		role.setPermissions(permissionConfig.getPermissionCode(permissionIds));
		securityService.updateRole(role);
		return AjaxResultUtils.refresh(getMessage("role.edit.success"),
				"role-list");
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<PermissionGroup> getPermissionGroups() {
		return permissionGroups;
	}

	public void setPermissionGroups(List<PermissionGroup> permissionGroups) {
		this.permissionGroups = permissionGroups;
	}

	public List<Integer> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Integer> permissionIds) {
		this.permissionIds = permissionIds;
	}
}

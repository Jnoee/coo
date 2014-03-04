package coo.mvc.security.blank.actions.system;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageConfig;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.core.security.permission.PermissionConfig;
import coo.mvc.security.blank.entity.Role;
import coo.mvc.security.blank.service.SecurityService;
import coo.mvc.util.DwzResultUtils;

/**
 * 角色管理。
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class RoleAction {
	@Resource
	private SecurityService securityService;
	@Resource
	private PermissionConfig permissionConfig;
	@Resource
	private MessageConfig messageConfig;

	/**
	 * 查看角色列表。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("role-list")
	public void list(Model model) {
		model.addAttribute("roles", securityService.getAllRole());
	}

	/**
	 * 新增角色。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("role-add")
	public void add(Model model) {
		model.addAttribute(new Role());
		model.addAttribute("permissionIds", new ArrayList<String>());
		model.addAttribute("permissionGroups",
				permissionConfig.getPermissionGroups());
	}

	/**
	 * 保存角色。
	 * 
	 * @param role
	 *            角色
	 * @param permissionIds
	 *            权限ID集合
	 * @return 返回保存角色成功提示。
	 */
	@RequestMapping("role-save")
	public ModelAndView save(Role role, Integer[] permissionIds) {
		role.setPermissions(permissionConfig.getPermissionCode(Arrays
				.asList(permissionIds)));
		securityService.createRole(role);
		return DwzResultUtils.close(
				messageConfig.getString("role.add.success"), "role-list");
	}

	/**
	 * 编辑角色。
	 * 
	 * @param roleId
	 *            角色ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("role-edit")
	public void edit(String roleId, Model model) {
		Role role = securityService.getRole(roleId);
		model.addAttribute(role);
		model.addAttribute("permissionIds",
				permissionConfig.getPermissionIds(role.getPermissions()));
		model.addAttribute("permissionGroups",
				permissionConfig.getPermissionGroups());
	}

	/**
	 * 更新角色。
	 * 
	 * @param role
	 *            角色
	 * @param permissionIds
	 *            权限ID集合
	 * @return 返回提示信息。
	 */
	@RequestMapping("role-update")
	public ModelAndView update(Role role, Integer[] permissionIds) {
		role.setPermissions(permissionConfig.getPermissionCode(Arrays
				.asList(permissionIds)));
		securityService.updateRole(role);
		return DwzResultUtils.refresh(
				messageConfig.getString("role.edit.success"), "role-list");
	}

}

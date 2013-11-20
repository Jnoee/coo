package coo.struts.security.blank.actions.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import coo.base.model.Page;
import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.struts.actions.GenericAction;
import coo.struts.security.blank.entity.Actor;
import coo.struts.security.blank.entity.Organ;
import coo.struts.security.blank.entity.Role;
import coo.struts.security.blank.entity.User;
import coo.struts.security.blank.entity.UserSettings;
import coo.struts.security.blank.service.SecurityService;
import coo.struts.util.AjaxResultUtils;

/**
 * 用户管理。
 */
@Auth(AdminPermission.CODE)
public class UserAction extends GenericAction implements
		ModelDriven<SearchModel> {
	@Resource
	private SecurityService securityService;
	private SearchModel searchModel = new SearchModel();
	private Page<User> pageModel;
	private User user;
	private Actor defaultActor;
	private Organ rootOrgan;
	private List<Role> roles = new ArrayList<Role>();
	private String managePassword;
	private String defaultPassword;

	/**
	 * 查看用户列表。
	 * 
	 * @return 返回查看用户列表页面。
	 */
	@Action("user-list")
	public String list() {
		pageModel = securityService.searchUser(searchModel);
		return SUCCESS;
	}

	/**
	 * 新增用户。
	 * 
	 * @return 返回新增用户页面。
	 */
	@Action("user-add")
	public String add() {
		rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		roles = securityService.getAllRole();
		user = new User();
		defaultActor = new Actor();
		return SUCCESS;
	}

	/**
	 * 保存用户。
	 * 
	 * @return 返回保存用户成功提示。
	 */
	@Action("user-save")
	public String save() {
		UserSettings settings = new UserSettings();
		settings.setDefaultActor(defaultActor);
		user.setSettings(settings);
		securityService.createUser(user);
		return AjaxResultUtils.close(getMessage("user.add.success"),
				"user-list");
	}

	/**
	 * 编辑用户。
	 * 
	 * @return 返回编辑用户页面。
	 */
	@Action("user-edit")
	public String view() {
		String userId = request.getParameter("userId");
		user = securityService.getUser(userId);
		return SUCCESS;
	}

	/**
	 * 更新用户。
	 * 
	 * @return 返回更新用户成功提示。
	 */
	@Action("user-update")
	public String update() {
		securityService.updateUser(user);
		return AjaxResultUtils.close(getMessage("user.edit.success"),
				"user-edit");
	}

	/**
	 * 启用用户。
	 * 
	 * @return 返回启用用户成功提示。
	 */
	@Action("user-enable")
	public String enable() {
		String userId = request.getParameter("userId");
		securityService.enableUser(userId);
		return AjaxResultUtils.refresh(getMessage("user.enable.success"),
				"user-list");
	}

	/**
	 * 禁用用户。
	 * 
	 * @return 返回禁用用户成功提示。
	 */
	@Action("user-disable")
	public String disable() {
		String userId = request.getParameter("userId");
		securityService.disableUser(userId);
		return AjaxResultUtils.refresh(getMessage("user.disable.success"),
				"user-list");
	}

	/**
	 * 重置用户密码。
	 * 
	 * @return 返回重置用户密码页面。
	 */
	@Action("user-pwd-reset")
	public String pwdReset() {
		String userId = request.getParameter("userId");
		user = securityService.getUser(userId);
		defaultPassword = AdminPermission.DEFAULT_PASSWORD;
		return SUCCESS;
	}

	/**
	 * 保存重置密码。
	 * 
	 * @return 返回保存重置密码成功提示。
	 */
	@Action("user-pwd-reset-save")
	public String pwdResetSave() {
		securityService.resetPassword(managePassword, user.getId());
		return AjaxResultUtils.close(getMessage("user.pwd.reset.success"));
	}

	@Override
	public SearchModel getModel() {
		return searchModel;
	}

	public Page<User> getPageModel() {
		return pageModel;
	}

	public void setPageModel(Page<User> pageModel) {
		this.pageModel = pageModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Actor getDefaultActor() {
		return defaultActor;
	}

	public void setDefaultActor(Actor defaultActor) {
		this.defaultActor = defaultActor;
	}

	public Organ getRootOrgan() {
		return rootOrgan;
	}

	public void setRootOrgan(Organ rootOrgan) {
		this.rootOrgan = rootOrgan;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getManagePassword() {
		return managePassword;
	}

	public void setManagePassword(String managePassword) {
		this.managePassword = managePassword;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
}

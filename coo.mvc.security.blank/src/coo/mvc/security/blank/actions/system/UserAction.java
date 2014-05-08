package coo.mvc.security.blank.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageSource;
import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.security.blank.entity.Actor;
import coo.mvc.security.blank.entity.User;
import coo.mvc.security.blank.entity.UserSettings;
import coo.mvc.security.blank.service.SecurityService;
import coo.mvc.util.DialogResultUtils;
import coo.mvc.util.NavTabResultUtils;

/**
 * 用户管理。
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class UserAction {
	@Resource
	private SecurityService securityService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 绑定用户。
	 * 
	 * @param binder
	 *            数据绑定对象
	 */
	@InitBinder("user")
	public void initBinderUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}

	/**
	 * 绑定职务。
	 * 
	 * @param binder
	 *            数据绑定对象
	 */
	@InitBinder("actor")
	public void initBinderActor(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("actor.");
	}

	/**
	 * 查看用户列表。
	 * 
	 * @param model
	 *            数据模型
	 * @param searchModel
	 *            搜索条件
	 */
	@RequestMapping("user-list")
	public void list(Model model, SearchModel searchModel) {
		model.addAttribute("userPage", securityService.searchUser(searchModel));
	}

	/**
	 * 新增用户。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("user-add")
	public void add(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("actor", new Actor());
		model.addAttribute("rootOrgan", securityService.getCurrentUser()
				.getSettings().getDefaultActor().getOrgan());
		model.addAttribute("roles", securityService.getAllRole());
	}

	/**
	 * 保存用户。
	 * 
	 * @param user
	 *            用户
	 * @param actor
	 *            职务
	 * @return 返回提示信息。
	 */
	@RequestMapping("user-save")
	public ModelAndView save(User user, Actor actor) {
		UserSettings settings = new UserSettings();
		settings.setDefaultActor(actor);
		user.setSettings(settings);
		securityService.createUser(user);
		return DialogResultUtils.closeAndReloadNavTab(messageSource
				.get("user.add.success"));
	}

	/**
	 * 编辑用户。
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("user-edit")
	public void edit(String userId, Model model) {
		model.addAttribute(securityService.getUser(userId));
	}

	/**
	 * 更新用户。
	 * 
	 * @param user
	 *            用户
	 * @return 返回提示信息。
	 */
	@RequestMapping("user-update")
	public ModelAndView update(User user) {
		securityService.updateUser(user);
		return DialogResultUtils.closeAndReloadNavTab(messageSource
				.get("user.edit.success"));
	}

	/**
	 * 禁用用户。
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回提示信息。
	 */
	@RequestMapping("user-disable")
	public ModelAndView disable(String userId) {
		securityService.disableUser(userId);
		return NavTabResultUtils.reload(messageSource
				.get("user.disable.success"));
	}

	/**
	 * 启用用户。
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回提示信息。
	 */
	@RequestMapping("user-enable")
	public ModelAndView enable(String userId) {
		securityService.enableUser(userId);
		return NavTabResultUtils.reload(messageSource
				.get("user.enable.success"));
	}

	/**
	 * 重置用户密码。
	 * 
	 * @param userId
	 *            用户ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("user-pwd-reset")
	public void pwdReset(String userId, Model model) {
		model.addAttribute(securityService.getUser(userId));
		model.addAttribute("defaultPassword", AdminPermission.DEFAULT_PASSWORD);
	}

	/**
	 * 保存重置密码。
	 * 
	 * @param managePassword
	 *            管理员密码
	 * @param user
	 *            用户
	 * @return 返回提示信息。
	 */
	@RequestMapping("user-pwd-reset-save")
	public ModelAndView pwdResetSave(String managePassword, User user) {
		securityService.resetPassword(managePassword, user.getId());
		return DialogResultUtils.close(messageSource
				.get("user.pwd.reset.success"));
	}
}

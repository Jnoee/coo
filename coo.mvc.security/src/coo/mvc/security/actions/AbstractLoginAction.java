package coo.mvc.security.actions;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.base.exception.UncheckedException;
import coo.core.security.model.LoginModel;
import coo.core.security.service.AbstractSecurityService;

/**
 * 无验证码登录。
 */
public abstract class AbstractLoginAction {
	@Resource
	protected AbstractSecurityService<?, ?, ?, ?, ?> securityService;

	/**
	 * 查看登录页面。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("login")
	public void login(Model model) {
		model.addAttribute(new LoginModel());
	}

	/**
	 * 验证登录。
	 * 
	 * @param loginModel
	 *            登录数据模型
	 * @param errors
	 *            错误信息
	 * @param model
	 *            数据模型
	 * 
	 * @return 登录成功返回系统首页，失败返回登录页面。
	 */
	@RequestMapping("login-auth")
	public String auth(LoginModel loginModel, BindingResult errors, Model model) {
		try {
			securityService.signIn(loginModel.getUsername(),
					loginModel.getPassword());
		} catch (UncheckedException e) {
			model.addAttribute(loginModel);
			errors.reject(e.getMessage());
			return "login";
		}
		return "redirect:/index";
	}

	/**
	 * 退出登录。
	 * 
	 * @return 返回登录页面。
	 */
	@RequestMapping("logout")
	public String logout() {
		securityService.signOut();
		return "redirect:/login";
	}
}

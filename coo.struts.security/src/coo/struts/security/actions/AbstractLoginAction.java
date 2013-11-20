package coo.struts.security.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;

import coo.base.exception.UncheckedException;
import coo.core.security.model.LoginModel;
import coo.core.security.service.AbstractSecurityService;
import coo.struts.actions.GenericAction;

/**
 * 用于用户名/密码登录的Action。
 */
public abstract class AbstractLoginAction extends GenericAction implements
		ModelDriven<LoginModel> {
	@Resource
	protected AbstractSecurityService<?, ?, ?, ?, ?> securityService;
	protected LoginModel loginModel = new LoginModel();

	/**
	 * 验证登录。
	 * 
	 * @return 返回系统首页。
	 */
	@Action(value = "login-auth", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "index" }),
			@Result(name = "input", location = "login.ftl") })
	public String auth() {
		try {
			securityService.signIn(loginModel.getUsername(),
					loginModel.getPassword());
		} catch (UncheckedException e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 退出登录。
	 * 
	 * @return 返回登录页面。
	 */
	@Action(value = "logout", results = { @Result(name = "success", type = "redirectAction", params = {
			"actionName", "login" }) })
	public String logout() {
		securityService.signOut();
		return SUCCESS;
	}

	@Override
	public LoginModel getModel() {
		return loginModel;
	}
}

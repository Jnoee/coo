package coo.struts.security.actions;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import coo.base.exception.BusinessException;
import coo.struts.security.component.AuthCounter;
import coo.struts.security.component.Captcha;

/**
 * 用于用户名/密码登录的Action。如果验证错误次数超过限制，将增加验证码辅助验证。
 */
public abstract class AbstractLoginWithCaptchaAction extends
		AbstractLoginAction {
	@Resource
	protected Captcha captcha;
	@Resource
	protected AuthCounter authCounter;

	@Override
	@Action(value = "login-auth", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "index" }),
			@Result(name = "input", location = "login.ftl") })
	public String auth() {
		if (authCounter.isOver()) {
			if (!captcha.validate()) {
				addActionError(getMessage("security.code.wrong"));
				return INPUT;
			}
		}
		try {
			securityService.signIn(loginModel.getUsername(),
					loginModel.getPassword());
			authCounter.clean();
		} catch (BusinessException e) {
			addActionError(e.getMessage());
			authCounter.add();
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 输出验证码图片。
	 * 
	 * @return 不返回页面，直接输出。
	 * @throws Exception
	 *             图片输出失败时抛出异常。
	 */
	@Action(value = "captcha-code-image")
	public String captchaCode() throws Exception {
		ImageIO.write(captcha.generateImage(), "JPEG",
				response.getOutputStream());
		return NONE;
	}

	public Captcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}

	public AuthCounter getAuthCounter() {
		return authCounter;
	}

	public void setAuthCounter(AuthCounter authCounter) {
		this.authCounter = authCounter;
	}
}

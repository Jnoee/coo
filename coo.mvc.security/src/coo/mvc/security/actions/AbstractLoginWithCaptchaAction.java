package coo.mvc.security.actions;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.base.exception.UncheckedException;
import coo.core.security.model.LoginModel;
import coo.mvc.security.component.AuthCounter;
import coo.mvc.security.component.Captcha;

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
	public void login(Model model) {
		super.login(model);
		model.addAttribute(captcha);
		model.addAttribute(authCounter);
	}

	@Override
	public String auth(LoginModel loginModel, BindingResult result, Model model) {
		if (authCounter.isOver()) {
			if (!captcha.validate()) {
				model.addAttribute(loginModel);
				model.addAttribute(captcha);
				model.addAttribute(authCounter);
				result.reject("验证码错误。");
				return "login";
			}
		}
		try {
			securityService.signIn(loginModel.getUsername(),
					loginModel.getPassword());
			authCounter.clean();
		} catch (UncheckedException e) {
			result.reject(e.getMessage());
			authCounter.add();
			model.addAttribute(loginModel);
			model.addAttribute(captcha);
			model.addAttribute(authCounter);
			return "login";
		}
		return "redirect:/index";
	}

	/**
	 * 输出验证码图片。
	 * 
	 * @param out
	 *            页面输出流
	 * 
	 * @throws Exception
	 *             图片输出失败时抛出异常。
	 */
	@RequestMapping("captcha-code-image")
	public void captchaCode(OutputStream out) throws Exception {
		ImageIO.write(captcha.generateImage(), "JPEG", out);
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

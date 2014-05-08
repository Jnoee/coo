package coo.mvc.boot.core.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.core.security.model.PwdChangeModel;
import coo.mvc.boot.core.service.SecurityService;
import coo.mvc.util.DialogResultUtils;

/**
 * 个人管理。
 */
@Controller
@RequestMapping("/system")
@Auth
public class PersonAction {
	@Resource
	private SecurityService securityService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 修改个人密码。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("person-pwd-change")
	public void pwdChange(Model model) {
		model.addAttribute(new PwdChangeModel());
	}

	/**
	 * 保存个人密码。
	 * 
	 * @param pwdChangeModel
	 *            修改密码表单Model
	 * @return 返回保存个人密码提示信息。
	 */
	@RequestMapping("person-pwd-change-save")
	public ModelAndView pwdChangeSave(PwdChangeModel pwdChangeModel) {
		securityService.changePassword(pwdChangeModel.getOldPwd(),
				pwdChangeModel.getNewPwd());
		return DialogResultUtils.close(messageSource
				.get("person.pwd.change.success"));
	}

	/**
	 * 切换职务。
	 * 
	 * @param actorId
	 *            职务ID
	 * @return 返回主页。
	 */
	@RequestMapping("person-actor-change")
	public ModelAndView change(String actorId) {
		securityService.changeActor(actorId);
		return new ModelAndView("redirect:/index");
	}
}

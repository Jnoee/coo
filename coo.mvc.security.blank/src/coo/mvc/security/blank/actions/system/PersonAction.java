package coo.mvc.security.blank.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageConfig;
import coo.core.security.annotations.Auth;
import coo.core.security.model.PwdChangeModel;
import coo.mvc.security.blank.service.SecurityService;
import coo.mvc.util.DwzResultUtils;

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
	private MessageConfig messageConfig;

	/**
	 * 修改个人密码。
	 * 
	 * @return 返回修改个人密码页面。
	 */
	@RequestMapping("person-pwd-change")
	public void pwdChange(Model model) {
		model.addAttribute(new PwdChangeModel());
	}

	/**
	 * 保存个人密码。
	 * 
	 * @return 返回保存个人密码成功提示。
	 */
	@RequestMapping("person-pwd-change-save")
	public ModelAndView pwdChangeSave(PwdChangeModel pwdChangeModel) {
		securityService.changePassword(pwdChangeModel.getOldPwd(),
				pwdChangeModel.getNewPwd());
		return DwzResultUtils.close(messageConfig
				.getString("person.pwd.change.success"));
	}

	/**
	 * 切换职务。
	 * 
	 * @return 返回主页。
	 */
	@RequestMapping("person-actor-change")
	public ModelAndView change(String actorId) {
		securityService.changeActor(actorId);
		return new ModelAndView("redirect:/index");
	}
}

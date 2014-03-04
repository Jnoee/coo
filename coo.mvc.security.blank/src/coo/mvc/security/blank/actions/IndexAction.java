package coo.mvc.security.blank.actions;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;
import coo.mvc.security.blank.service.SecurityService;

/**
 * 主页。
 */
@Controller
@RequestMapping("/")
@Auth
public class IndexAction {
	@Resource
	private SecurityService securityService;

	/**
	 * 查看主页。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("index")
	public void index(Model model) {
		model.addAttribute("currentUser", securityService.getCurrentUser());
	}
}

package coo.mvc.security.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;

/**
 * 首页。
 */
@Controller
@RequestMapping("/")
@Auth
public class HomeAction {
	/**
	 * 查看首页。
	 */
	@RequestMapping("home")
	public void home() {
	}
}
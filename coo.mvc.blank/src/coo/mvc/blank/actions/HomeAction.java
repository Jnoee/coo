package coo.mvc.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页。
 */
@Controller
@RequestMapping("/")
public class HomeAction {
	/**
	 * 查看首页。
	 */
	@RequestMapping("home")
	public void home() {
	}
}
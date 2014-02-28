package coo.mvc.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页。
 */
@Controller
@RequestMapping("/")
public class IndexAction {
	/**
	 * 查看主页。
	 */
	@RequestMapping("index")
	public void index() {
	}
}

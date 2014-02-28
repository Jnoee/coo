package coo.mvc.blank.actions.module2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 示例模块2的子菜单。
 */
@Controller("module2.menu")
@RequestMapping("/module2")
public class MenuAction {
	/**
	 * 查看子菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
	}
}

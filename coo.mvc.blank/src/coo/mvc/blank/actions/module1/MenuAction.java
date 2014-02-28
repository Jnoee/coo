package coo.mvc.blank.actions.module1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 示例模块1的子菜单。
 */
@Controller("module1.menu")
@RequestMapping("/module1")
public class MenuAction {
	/**
	 * 查看子菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
	}
}

package coo.mvc.blank.actions.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公司管理子菜单。
 */
@Controller("company.menu")
@RequestMapping("/company")
public class MenuAction {
	/**
	 * 查看子菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
	}
}

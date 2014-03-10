package coo.mvc.boot.demo.actions.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;

/**
 * 公司管理菜单。
 */
@Controller("company.menu")
@RequestMapping("/company")
@Auth({ "COMPANY_MANAGE", "EMPLOYEE_MANAGE" })
public class MenuAction {
	/**
	 * 查看菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
	}
}

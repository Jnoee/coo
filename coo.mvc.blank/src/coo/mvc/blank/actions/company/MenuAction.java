package coo.mvc.blank.actions.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("company.menu")
@RequestMapping("/company")
public class MenuAction {
	@RequestMapping("menu")
	public void menu() {
	}
}

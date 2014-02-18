package coo.mvc.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MenuAction {
	@RequestMapping("company/menu")
	public String companyMenu() {
		return "company/menu";
	}

	@RequestMapping("module1/menu")
	public String module1Menu() {
		return "module1/menu";
	}

	@RequestMapping("module2/menu")
	public String module2Menu() {
		return "module2/menu";
	}
}

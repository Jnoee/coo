package coo.mvc.blank.actions.module2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("module2.menu")
@RequestMapping("/module2")
public class MenuAction {
	@RequestMapping("menu")
	public void menu() {
	}
}

package coo.mvc.blank.actions.module1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("module1.menu")
@RequestMapping("/module1")
public class MenuAction {
	@RequestMapping("menu")
	public void menu() {
	}
}

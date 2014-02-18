package coo.mvc.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeAction {
	@RequestMapping("home")
	public String home() {
		return "home";
	}
}
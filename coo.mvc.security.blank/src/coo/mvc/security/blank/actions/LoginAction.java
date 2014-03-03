package coo.mvc.security.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.mvc.security.actions.AbstractLoginAction;

/**
 * 登录。
 */
@Controller
@RequestMapping("/")
public class LoginAction extends AbstractLoginAction {
}

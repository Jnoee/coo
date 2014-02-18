package coo.mvc.blank.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.base.exception.UncheckedException;
import coo.mvc.util.ResultUtils;

/**
 * 主页。
 */
@Controller
@RequestMapping("/")
public class IndexAction {
	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("runtime")
	public String runtime() {
		throw new NullPointerException();
	}

	@RequestMapping("failure")
	public ModelAndView failure() {
		return ResultUtils.failure("失败。", "index");
	}

	@RequestMapping("success")
	public ModelAndView success() {
		return ResultUtils.success("成功。", "index");
	}

	@RequestMapping("operate")
	public String operate() {
		throw new UncheckedException("operate error");
	}
}

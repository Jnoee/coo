package coo.mvc.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 常规异常处理。
 */
public class GenericExceptionResolver extends SimpleMappingExceptionResolver {
	/**
	 * 构造方法。
	 */
	public GenericExceptionResolver() {
		setExceptionAttribute("ex");
		setDefaultErrorView("500");
		addStatusCode("500", HttpStatus.INTERNAL_SERVER_ERROR.value());
		addStatusCode("404", HttpStatus.NOT_FOUND.value());
		addStatusCode("403", HttpStatus.FORBIDDEN.value());
		setWarnLogCategory(getClass().getName());
	}
}

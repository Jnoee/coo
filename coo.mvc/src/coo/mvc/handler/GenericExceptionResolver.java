package coo.mvc.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 常规异常处理。
 */
public class GenericExceptionResolver extends SimpleMappingExceptionResolver {
	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 构造方法。
	 */
	public GenericExceptionResolver() {
		setExceptionAttribute("ex");
		setDefaultErrorView("500");
		addStatusCode("500", HttpStatus.INTERNAL_SERVER_ERROR.value());
		addStatusCode("404", HttpStatus.NOT_FOUND.value());
		addStatusCode("403", HttpStatus.FORBIDDEN.value());
	}

	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		log.warn(buildLogMessage(ex, request), ex);
	}
}

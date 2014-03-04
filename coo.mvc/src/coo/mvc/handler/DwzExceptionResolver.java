package coo.mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import coo.base.exception.BusinessException;
import coo.mvc.model.DwzResult;

/**
 * DWZ异常处理。
 */
public class DwzExceptionResolver extends GenericExceptionResolver {
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView mav = super.doResolveException(request, response, handler,
				exception);
		if (isAjaxRequest(request)) {
			response.setStatus(HttpStatus.OK.value());
			mav.setViewName("dwz-result");
			mav.addObject("ajaxResult", DwzResult.fail());
			processCustomExceptions(mav, exception);
		}
		return mav;
	}

	/**
	 * 判断客户端请求是否是Ajax请求。
	 * 
	 * @param request
	 *            请求对象
	 * 
	 * @return 如果客户端请求是Ajax请求返回true，否则返回false。
	 */
	protected Boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request
				.getHeader("X-Requested-With"));
	}

	/**
	 * 处理自定义异常。
	 * 
	 * @param mav
	 *            数据模型
	 * @param exception
	 *            异常
	 */
	protected void processCustomExceptions(ModelAndView mav, Exception exception) {
		if (exception instanceof BusinessException) {
			mav.addObject("ajaxResult", DwzResult.error(exception.getMessage()));
		}
	}
}

package coo.mvc.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import coo.base.exception.BusinessException;
import coo.mvc.handler.DwzExceptionResolver;
import coo.mvc.model.DwzResult;

/**
 * 基于安全模块的DWZ异常处理。
 */
public class DwzSecurityExceptionResolver extends DwzExceptionResolver {
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView mav = super.doResolveException(request, response, handler,
				exception);
		if (isAjaxRequest(request)) {
			response.setStatus(HttpStatus.OK.value());
			mav.setViewName("dwz-result");
			mav.addObject("ajaxResult", DwzResult.fail());
			if (exception instanceof BusinessException) {
				mav.addObject("ajaxResult",
						DwzResult.error(exception.getMessage()));
			}
			if (exception instanceof UnauthenticatedException) {
				mav.addObject("ajaxResult", DwzResult.timeout("会话已超时，请重新登录。"));
			}
			if (exception instanceof UnauthorizedException) {
				mav.addObject("ajaxResult",
						DwzResult.error("您没有执行该操作的权限，请与管理员联系。"));
			}
		}
		return mav;
	}
}

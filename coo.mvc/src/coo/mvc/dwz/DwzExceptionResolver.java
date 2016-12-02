package coo.mvc.dwz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import coo.base.exception.BusinessException;
import coo.mvc.handler.GenericExceptionResolver;
import coo.mvc.util.HttpUtils;

/**
 * DWZ异常处理。
 */
public class DwzExceptionResolver extends GenericExceptionResolver {
  @Override
  protected ModelAndView doResolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception exception) {
    ModelAndView mav = super.doResolveException(request, response, handler, exception);
    if (HttpUtils.isAjaxRequest(request)) {
      response.setStatus(HttpStatus.OK.value());
      mav = processCustomExceptions(exception);
    }
    return mav;
  }

  /**
   * 处理自定义异常。
   * 
   * @param exception 异常
   * @return 返回异常数据模型。
   */
  protected ModelAndView processCustomExceptions(Exception exception) {
    ModelAndView mav = new DwzResultBuild().fail().build();
    if (exception instanceof BusinessException) {
      mav = new DwzResultBuild().error(exception.getMessage()).build();
    }
    return mav;
  }
}

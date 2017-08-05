package coo.mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import coo.base.exception.BusinessException;
import coo.mvc.dwz.DwzResultBuild;
import coo.mvc.util.HttpUtils;

/**
 * DWZ异常处理组件。
 */
public class DwzErrorController extends BasicErrorController {
  protected final ErrorAttributes errorAttributes;

  /**
   * 构造方法。
   * 
   * @param errorAttributes ErrorAttributes组件
   * @param errorProperties ErrorProperties组件
   */
  public DwzErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
    super(errorAttributes, errorProperties);
    this.errorAttributes = errorAttributes;
  }

  @Override
  public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
    if (HttpUtils.isAjaxRequest(request)) {
      return processErrorAjax(request, response);
    } else {
      return processErrorHtml(request, response);
    }
  }

  /**
   * 处理HTTP请求异常。
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return 返回异常页面。
   */
  protected ModelAndView processErrorHtml(HttpServletRequest request,
      HttpServletResponse response) {
    response.setStatus(getStatus(request).value());
    return new ModelAndView("/500");
  }

  /**
   * 处理AJAX请求异常。
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return 返回异常页面。
   */
  protected ModelAndView processErrorAjax(HttpServletRequest request,
      HttpServletResponse response) {
    response.setStatus(HttpStatus.OK.value());
    ModelAndView mav = new DwzResultBuild().fail().build();
    Throwable ex = getException(request);
    if (ex instanceof BusinessException) {
      mav = new DwzResultBuild().error(ex.getMessage()).build();
    }
    return mav;
  }

  /**
   * 获取异常。
   * 
   * @param request 请求对象
   * @return 返回异常对象。
   */
  protected Throwable getException(HttpServletRequest request) {
    return errorAttributes.getError(new ServletRequestAttributes(request));
  }
}

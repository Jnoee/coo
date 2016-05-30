package coo.mvc.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * API Action基类。
 */
public abstract class ApiAction {
  @Value("${api.log.enabled:false}")
  private Boolean logEnabled = false;

  /**
   * 异常拦截处理。
   * 
   * @param request 请求对象
   * @param ex 异常
   * @return 返回响应对象。
   */
  @ExceptionHandler(value = {ApiException.class, BindException.class, Exception.class})
  protected ApiReply handleException(HttpServletRequest request, Exception ex) {
    ApiReply reply = ApiUtils.newErrorReply();
    if (ex instanceof ApiException) {
      reply = ApiUtils.newApiExceptionReply((ApiException) ex);
    }
    if (ex instanceof BindException) {
      reply = ApiUtils.newBindExceptionReply((BindException) ex);
    }
    if (reply.getCode().equals(ApiCode.FAILURE)) {
      ApiUtils.log(request, reply, ex);
    } else if (logEnabled) {
      ApiUtils.log(request, reply);
    }
    return reply;
  }
}

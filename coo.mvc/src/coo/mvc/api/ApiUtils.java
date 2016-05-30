package coo.mvc.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import coo.base.util.StringUtils;
import coo.core.message.MessageSource;
import coo.core.util.SpringUtils;

/**
 * API工具类。
 */
public class ApiUtils {
  private static final Logger log = LoggerFactory.getLogger(ApiUtils.class);
  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * 抛出API异常。
   * 
   * @param code 异常编码
   * @param vars 异常信息变量
   */
  public static void thrown(String code, Object... vars) {
    throw newExcepiton(code, vars);
  }

  /**
   * 生成API异常。
   * 
   * @param code 异常编码
   * @param vars 异常信息变量
   * @return 返回生成的API异常。
   */
  public static ApiException newExcepiton(String code, Object... vars) {
    MessageSource messageSource = SpringUtils.getBean("messageSource");
    return new ApiException(code, messageSource.get(code, vars));
  }

  /**
   * 生成失败的响应对象。
   * 
   * @return 返回失败的响应对象。
   */
  public static ApiReply newErrorReply() {
    return new ApiReply(ApiCode.FAILURE, "服务器繁忙，请稍后再试。");
  }

  /**
   * 生成API异常响应对象。
   * 
   * @param ex API异常
   * @return 返回API异常响应对象。
   */
  public static ApiReply newApiExceptionReply(ApiException ex) {
    return new ApiReply(ex.getCode(), ex.getMsg());
  }

  /**
   * 生成参数验证异常响应对象。
   * 
   * @param ex 参数验证异常
   * @return 返回参数验证异常响应对象。
   */
  public static ApiReply newBindExceptionReply(BindException ex) {
    List<String> errorMsgs = new ArrayList<>();
    List<ObjectError> errors = ((BindException) ex).getBindingResult().getAllErrors();
    for (ObjectError error : errors) {
      errorMsgs.add(error.getDefaultMessage());
    }
    return new ApiReply(ApiCode.PARAMS_ERROR, StringUtils.join(errorMsgs, "|"));
  }

  /**
   * 从数据模型中获取API响应对象。
   * 
   * @param modelMap 数据模型
   * @return 返回API响应对象。
   */
  public static ApiReply getReply(ModelMap modelMap) {
    for (Object model : modelMap.values()) {
      if (model instanceof ApiReply) {
        return (ApiReply) model;
      }
    }
    return null;
  }

  /**
   * 记录API调用日志。
   * 
   * @param request 请求对象
   * @param reply 响应对象
   */
  public static void log(HttpServletRequest request, ApiReply reply) {
    String result = reply.getCode().equals(ApiCode.SUCCESS) ? "成功" : "失败";
    String msg = String.format("[%s][%s][请求：%s][响应：%s]", request.getRequestURI(), result,
        getQueryJson(request), getReplyJson(reply));
    log.info(msg);
  }

  /**
   * 记录API异常日志。
   * 
   * @param request 请求对象
   * @param reply 响应对象
   * @param ex 异常
   */
  public static void log(HttpServletRequest request, ApiReply reply, Exception ex) {
    String msg = String.format("[%s][失败][请求：%s][响应：%s]", request.getRequestURI(),
        getQueryJson(request), getReplyJson(reply));
    if (ex instanceof ApiException) {
      log.error(msg);
    } else {
      log.error(msg, ex);
    }
  }

  /**
   * 获取请求对象的JSON字符串，用于日志记录。
   * 
   * @param request 请求对象
   * @return 返回请求对象的JSON字符串。
   */
  private static String getQueryJson(HttpServletRequest request) {
    try {
      return mapper.writeValueAsString(request.getParameterMap());
    } catch (JsonProcessingException e) {
      return e.getMessage();
    }
  }

  /**
   * 获取响应对象的JSON字符串，用于日志记录。
   * 
   * @param reply 响应对象。
   * @return 返回响应对象的JSON字符串。
   */
  private static String getReplyJson(ApiReply reply) {
    try {
      return mapper.writeValueAsString(reply);
    } catch (JsonProcessingException e) {
      return e.getMessage();
    }
  }
}

package coo.mvc.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * 一般Http请求结果的处理工具类。
 */
public class ResultUtils {
  /**
   * 操作成功。
   * 
   * @param message 成功的提示信息
   * @param returnUrl 待返回的页面URL
   * @return 返回操作成功页面。
   */
  public static ModelAndView success(String message, String returnUrl) {
    ModelAndView model = new ModelAndView("global-success");
    model.addObject("message", message);
    model.addObject("returnUrl", returnUrl);
    return model;
  }

  /**
   * 操作失败。
   * 
   * @param message 失败的提示信息
   * @param returnUrl 待返回的页面URL
   * @return 返回操作失败页面。
   */
  public static ModelAndView fail(String message, String returnUrl) {
    ModelAndView model = new ModelAndView("global-failure");
    model.addObject("message", message);
    model.addObject("returnUrl", returnUrl);
    return model;
  }

  /**
   * 私有构造方法。
   */
  private ResultUtils() {}
}

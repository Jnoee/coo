package coo.mvc.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Http工具类。
 */
public class HttpUtils {
  /**
   * 判断是否AJAX请求。
   * 
   * @param request 请求对象
   * @return 返回是否AJAX请求。
   */
  public static Boolean isAjaxRequest(HttpServletRequest request) {
    return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
  }

  /**
   * 判断是否IOS请求。
   * 
   * @param request 请求对象
   * @return 返回是否IOS请求。
   */
  public static Boolean isIosRequest(HttpServletRequest request) {
    String ua = request.getHeader("User-Agent").toLowerCase();
    return ua.contains("mac") || ua.contains("iphone") || ua.contains("ipad");
  }

  /**
   * 私有构造方法。
   */
  private HttpUtils() {}
}

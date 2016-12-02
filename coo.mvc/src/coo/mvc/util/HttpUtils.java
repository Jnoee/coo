package coo.mvc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;

/**
 * Http工具类。
 */
public class HttpUtils {
  /**
   * URL编码。（默认UTF-8）
   * 
   * @param url URL
   * @return 返回编码后的URL。
   */
  public static String encode(String url) {
    return encode(url, Encoding.UTF_8);
  }

  /**
   * URL编码。
   * 
   * @param url URL
   * @param encoding 编码格式
   * @return 返回编码后的URL。
   */
  public static String encode(String url, String encoding) {
    try {
      return URLEncoder.encode(url, encoding);
    } catch (UnsupportedEncodingException e) {
      throw new UncheckedException("进行URL编码时发生异常。", e);
    }
  }

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
}

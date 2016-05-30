package coo.mvc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
}

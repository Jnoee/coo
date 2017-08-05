package coo.base.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;

/**
 * URL工具类。
 */
public class UrlUtils {
  /**
   * 对字符串进行编码。
   * 
   * @param content 字符串
   * @return 返回编码后的字符串。
   */
  public static String encode(String content) {
    return encode(content, Encoding.UTF_8);
  }

  /**
   * 对字符串进行编码。
   * 
   * @param content 字符串
   * @param encoding 编码格式
   * @return 返回编码后的字符串。
   */
  public static String encode(String content, String encoding) {
    try {
      return URLEncoder.encode(content, encoding);
    } catch (Exception e) {
      throw new UncheckedException("URL编码时发生异常。", e);
    }
  }

  /**
   * 对字符串进行解码。
   * 
   * @param content 字符串
   * @return 返回解码后的字符串。
   */
  public static String decode(String content) {
    return decode(content, Encoding.UTF_8);
  }

  /**
   * 对字符串进行解码。
   * 
   * @param content 字符串
   * @param encoding 解码格式
   * @return 返回解码后的字符串。
   */
  public static String decode(String content, String encoding) {
    try {
      return URLDecoder.decode(content, encoding);
    } catch (Exception e) {
      throw new UncheckedException("URL解码时发生异常。", e);
    }
  }

  /**
   * 私有构造方法。
   */
  private UrlUtils() {}
}

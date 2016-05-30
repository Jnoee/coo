package coo.base.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类。
 */
public class ThrowableUtils {
  /**
   * 获取指定Throwable对象的根Throwable对象。
   * 
   * @param throwable Throwable对象
   * @return 返回指定Throwable对象的根Throwable对象。
   */
  public static Throwable getRootThrowable(Throwable throwable) {
    if (throwable.getCause() != null) {
      return getRootThrowable(throwable.getCause());
    } else {
      return throwable;
    }
  }

  /**
   * 获取Throwable对象的异常堆栈信息。
   * 
   * @param throwable Throwable对象
   * @return 返回Throwable对象的异常堆栈信息。
   */
  public static String getStackTrace(Throwable throwable) {
    StringWriter stingWriter = new StringWriter();
    throwable.printStackTrace(new PrintWriter(stingWriter));
    return stingWriter.toString();
  }
}

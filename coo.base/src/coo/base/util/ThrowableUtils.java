package coo.base.util;

/**
 * 异常工具类。
 */
public class ThrowableUtils {
	/**
	 * 获取指定Throwable对象的根Throwable对象。
	 * 
	 * @param throwable
	 *            Throwable对象
	 * @return 返回指定Throwable对象的根Throwable对象。
	 */
	public static Throwable getRootThrowable(Throwable throwable) {
		if (throwable.getCause() != null) {
			return getRootThrowable(throwable.getCause());
		} else {
			return throwable;
		}
	}
}

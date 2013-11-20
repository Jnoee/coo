package coo.core.security.exception;

/**
 * 未登录异常。
 */
public class NotLogonException extends RuntimeException {
	/**
	 * 构造方法。
	 */
	public NotLogonException() {
		super("用户未登录或会话已过期。");
	}
}

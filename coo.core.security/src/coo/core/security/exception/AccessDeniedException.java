package coo.core.security.exception;

/**
 * 无访问权限异常。
 */
public class AccessDeniedException extends RuntimeException {
	/**
	 * 构造方法。
	 */
	public AccessDeniedException() {
		super("用户无访问权限。");
	}
}

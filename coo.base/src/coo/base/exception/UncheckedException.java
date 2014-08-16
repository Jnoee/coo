package coo.base.exception;

/**
 * 无需捕获处理的异常。
 */
@SuppressWarnings("serial")
public class UncheckedException extends RuntimeException {
	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            异常信息
	 */
	public UncheckedException(String message) {
		super(message);
	}

	/**
	 * 构造方法。
	 * 
	 * @param cause
	 *            异常原因
	 */
	public UncheckedException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常原因
	 */
	public UncheckedException(String message, Throwable cause) {
		super(message, cause);
	}
}

package coo.mvc.api;

/**
 * API异常。
 */
@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
	private String code;
	private String msg;

	public ApiException(String code, String msg) {
		super(code + ":" + msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
package coo.struts.model;

import java.io.Serializable;

/**
 * 一般Http请求结果的Model。
 */
public class HttpResultModel implements Serializable {
	private String message;
	private String returnUrl;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
}

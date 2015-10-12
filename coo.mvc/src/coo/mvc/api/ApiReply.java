package coo.mvc.api;

import com.thoughtworks.xstream.annotations.XStreamAliasType;

/**
 * API响应对象基类。
 */
@XStreamAliasType("reply")
public class ApiReply {
	private String code;
	private String msg;

	public ApiReply() {
		this(ApiCode.SUCCESS, "操作成功。");
	}

	public ApiReply(String code, String msg) {
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
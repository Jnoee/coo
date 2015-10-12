package coo.mvc.dwz;


/**
 * DWZ的Ajax响应内容Model。
 */
public class DwzResult {
	private String statusCode;
	private String message;
	private String navTabId = "";
	private String rel = "";
	private String callbackType = DwzCallbackType.NONE;
	private String forwardUrl = "";

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            navTab的ID
	 * @param rel
	 *            div或dialog的ID
	 * @param forwardUrl
	 *            跳转页面的URL
	 */
	private DwzResult(String message, String navTabId, String rel,
			String forwardUrl) {
		this.message = message;
		this.navTabId = navTabId;
		this.rel = rel;
		this.forwardUrl = forwardUrl;
	}

	/**
	 * 构建操作成功的响应对象。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            navTab的ID
	 * @param rel
	 *            div或dialog的ID
	 * @param forwardUrl
	 *            跳转页面的URL
	 * @return 返回操作成功的响应对象。
	 */
	public static DwzResult success(String message, String navTabId,
			String rel, String forwardUrl) {
		DwzResult result = new DwzResult(message, navTabId, rel, forwardUrl);
		result.setStatusCode(DwzStatusCode.OK);
		return result;
	}

	/**
	 * 构建关闭页面的操作成功的响应对象。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            navTab的ID
	 * @param rel
	 *            div或dialog的ID
	 * @param forwardUrl
	 *            跳转页面的URL
	 * @return 返回关闭页面的操作成功的响应对象。
	 */
	public static DwzResult successClose(String message, String navTabId,
			String rel, String forwardUrl) {
		DwzResult result = new DwzResult(message, navTabId, rel, forwardUrl);
		result.setStatusCode(DwzStatusCode.OK);
		result.setCallbackType(DwzCallbackType.CLOSE);
		return result;
	}

	/**
	 * 构建跳转页面的操作成功的响应对象。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            navTab的ID
	 * @param rel
	 *            div或dialog的ID
	 * @param forwardUrl
	 *            跳转页面的URL
	 * @return 返回跳转页面的操作成功的响应对象。
	 */
	public static DwzResult successForward(String message, String navTabId,
			String rel, String forwardUrl) {
		DwzResult result = new DwzResult(message, navTabId, rel, forwardUrl);
		result.setStatusCode(DwzStatusCode.OK);
		result.setCallbackType(DwzCallbackType.FORWARD);
		return result;
	}

	/**
	 * 构建用于提示错误的响应内容model。
	 * 
	 * @param message
	 *            错误信息
	 * @return 返回用于提示错误的响应内容model。
	 */
	public static DwzResult error(String message) {
		DwzResult result = new DwzResult(message, "", "", "");
		result.setStatusCode(DwzStatusCode.ERROR);
		return result;
	}

	/**
	 * 构建用于提示系统异常的响应内容model。
	 * 
	 * @return 返回用于提示系统异常的响应内容model。
	 */
	public static DwzResult fail() {
		DwzResult result = new DwzResult("服务器暂时繁忙，请稍候重试或与管理员联系。", "", "", "");
		result.setStatusCode(DwzStatusCode.ERROR);
		result.setCallbackType(DwzCallbackType.CLOSE);
		return result;
	}

	/**
	 * 构建用于提示权限限制的响应内容model。
	 * 
	 * @return 返回用于提示超时的响应内容model。
	 */
	public static DwzResult denied() {
		DwzResult result = new DwzResult("您没有执行该操作的权限，请与管理员联系。", "", "", "");
		result.setStatusCode(DwzStatusCode.ERROR);
		return result;
	}

	/**
	 * 构建用于提示超时的响应内容model。
	 * 
	 * @return 返回用于提示超时的响应内容model。
	 */
	public static DwzResult timeout() {
		DwzResult model = new DwzResult("会话已超时，请重新登录。", "", "", "");
		model.setStatusCode(DwzStatusCode.TIMEOUT);
		return model;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
}
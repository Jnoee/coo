package coo.mvc.model;

/**
 * DWZ的Ajax响应内容Model。
 */
public class DwzResult {
	private StatusCode statusCode;
	private String message;
	private String navTabId = "";
	private String rel = "";
	private CallbackType callbackType = CallbackType.NONE;
	private String forwardUrl = "";

	/**
	 * 构建用于刷新指定navTab的响应内容model。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @return 返回用于刷新的响应内容model。
	 */
	public static DwzResult refresh(String message, String navTabId) {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.OK);
		model.setMessage(message);
		model.setNavTabId(navTabId);
		return model;
	}

	/**
	 * 构建用于局部刷新或重新加载dialog的响应内容model。（不关闭当前dialog）
	 * 
	 * @param message
	 *            提示信息
	 * @param rel
	 *            待局部刷新容器ID
	 * @return 返回用于局部刷新的响应内容model。
	 */
	public static DwzResult flush(String message, String rel) {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.OK);
		model.setMessage(message);
		model.setRel(rel);
		return model;
	}

	/**
	 * 构建用于重新加载dialog的响应内容model。（关闭当前dialog）
	 * 
	 * @param message
	 *            提示信息
	 * @param rel
	 *            待重新加载dialog的ID
	 * @return 返回用于重新加载dialog的响应内容model。
	 */
	public static DwzResult reload(String message, String rel) {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.OK);
		model.setMessage(message);
		model.setRel(rel);
		model.setCallbackType(CallbackType.CLOSE_CURRENT);
		return model;
	}

	/**
	 * 构建用于刷新指定navTab并关闭当前页面的响应内容model。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @return 返回用于刷新并关闭当前页面的响应内容model。
	 */
	public static DwzResult close(String message, String navTabId) {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.OK);
		model.setMessage(message);
		model.setNavTabId(navTabId);
		model.setCallbackType(CallbackType.CLOSE_CURRENT);
		return model;
	}

	/**
	 * 构建用于刷新指定navTab并将当前页面跳转到其它页面的响应内容model。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @param forwardUrl
	 *            待跳转的url地址
	 * @return 返回用于刷新指定navTab并将当前页面跳转到其它页面的响应内容model。
	 */
	public static DwzResult forward(String message, String navTabId,
			String forwardUrl) {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.OK);
		model.setMessage(message);
		model.setNavTabId(navTabId);
		model.setCallbackType(CallbackType.FORWARD);
		model.setForwardUrl(forwardUrl);
		return model;
	}

	/**
	 * 构建用于提示错误的响应内容model。
	 * 
	 * @param message
	 *            错误信息
	 * @return 返回用于提示错误的响应内容model。
	 */
	public static DwzResult error(String message) {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.ERROR);
		model.setMessage(message);
		return model;
	}

	/**
	 * 构建用于提示系统异常的响应内容model。
	 * 
	 * @return 返回用于提示系统异常的响应内容model。
	 */
	public static DwzResult fail() {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.ERROR);
		model.setMessage("系统发生未知的异常，请稍后再试。");
		model.setCallbackType(CallbackType.CLOSE_CURRENT);
		return model;
	}

	/**
	 * 构建用于提示权限限制的响应内容model。
	 * 
	 * @return 返回用于提示超时的响应内容model。
	 */
	public static DwzResult denied() {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.ERROR);
		model.setMessage("您没有执行该操作的权限，请与管理员联系。");
		return model;
	}

	/**
	 * 构建用于提示超时的响应内容model。
	 * 
	 * @return 返回用于提示超时的响应内容model。
	 */
	public static DwzResult timeout() {
		DwzResult model = new DwzResult();
		model.setStatusCode(StatusCode.TIMEOUT);
		model.setMessage("会话已超时，请重新登录。");
		return model;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
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

	public CallbackType getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(CallbackType callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	/**
	 * 结果状态枚举值。
	 */
	protected enum StatusCode {
		OK("200"), ERROR("300"), TIMEOUT("301");

		private String value;

		/**
		 * 构造方法。
		 * 
		 * @param value
		 *            结果状态值
		 */
		private StatusCode(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	/**
	 * callback类型枚举值。
	 */
	protected enum CallbackType {
		NONE(""), CLOSE_CURRENT("closeCurrent"), FORWARD("forward");

		private String value;

		/**
		 * 构造方法。
		 * 
		 * @param value
		 *            callback类型
		 */
		private CallbackType(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}

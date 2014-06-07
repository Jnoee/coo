package coo.mvc.model;

import coo.mvc.constants.DwzCallbackType;
import coo.mvc.constants.DwzStatusCode;

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
	 */
	@Deprecated
	private DwzResult() {
	}

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
	 * 构建用于刷新指定navTab的响应内容model。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @return 返回用于刷新的响应内容model。
	 */
	@Deprecated
	public static DwzResult refresh(String message, String navTabId) {
		DwzResult result = new DwzResult();
		result.setStatusCode(DwzStatusCode.OK);
		result.setMessage(message);
		result.setNavTabId(navTabId);
		return result;
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
	@Deprecated
	public static DwzResult flush(String message, String rel) {
		DwzResult model = new DwzResult();
		model.setStatusCode(DwzStatusCode.OK);
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
	@Deprecated
	public static DwzResult reload(String message, String rel) {
		DwzResult model = new DwzResult();
		model.setStatusCode(DwzStatusCode.OK);
		model.setMessage(message);
		model.setRel(rel);
		model.setCallbackType(DwzCallbackType.CLOSE);
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
	@Deprecated
	public static DwzResult close(String message, String navTabId) {
		DwzResult model = new DwzResult();
		model.setStatusCode(DwzStatusCode.OK);
		model.setMessage(message);
		model.setNavTabId(navTabId);
		model.setCallbackType(DwzCallbackType.CLOSE);
		return model;
	}

	/**
	 * 构建用于刷新指定navTab并关闭当前页面及指定dialog的响应内容model。
	 * 
	 * @param message
	 *            提示信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @param rel
	 *            待关闭的dialog的ID
	 * @return 返回用于刷新并关闭当前页面及指定dialog的响应内容model。
	 */
	@Deprecated
	public static DwzResult close(String message, String navTabId, String rel) {
		DwzResult model = new DwzResult();
		model.setStatusCode(DwzStatusCode.OK);
		model.setMessage(message);
		model.setNavTabId(navTabId);
		model.setRel(rel);
		model.setCallbackType(DwzCallbackType.CLOSE);
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
	@Deprecated
	public static DwzResult forward(String message, String navTabId,
			String forwardUrl) {
		DwzResult model = new DwzResult();
		model.setStatusCode(DwzStatusCode.OK);
		model.setMessage(message);
		model.setNavTabId(navTabId);
		model.setCallbackType(DwzCallbackType.FORWARD);
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
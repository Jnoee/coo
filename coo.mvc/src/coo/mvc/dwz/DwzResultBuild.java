package coo.mvc.dwz;

import org.springframework.web.servlet.ModelAndView;

import coo.base.util.StringUtils;
import coo.core.message.MessageSource;
import coo.core.util.SpringUtils;
import coo.mvc.handler.GenericJacksonView;

/**
 * DWZ的Ajax响应内容构造器。
 */
public class DwzResultBuild {
	private DwzResult result = new DwzResult();

	/**
	 * 构建响应对象。
	 * 
	 * @return 返回响应对象。
	 */
	public ModelAndView build() {
		ModelAndView mv = new ModelAndView();
		GenericJacksonView view = new GenericJacksonView();
		view.setModelKey("dwzResult");
		mv.setView(view);
		mv.addObject(result);
		return mv;
	}

	/**
	 * 设置操作成功信息。
	 * 
	 * @param code
	 *            成功信息编码
	 * @param vars
	 *            成功信息变量
	 * @return 返回构建器。
	 */
	public DwzResultBuild success(String code, Object... vars) {
		result.setStatusCode(DwzConstants.OK);
		result.setMessage(getMessage(code, vars));
		return this;
	}

	/**
	 * 设置操作失败信息。
	 * 
	 * @param code
	 *            失败信息编码
	 * @param vars
	 *            失败信息变量
	 * @return 返回构建器。
	 */
	public DwzResultBuild error(String code, Object... vars) {
		result.setStatusCode(DwzConstants.ERROR);
		result.setMessage(getMessage(code, vars));
		return this;
	}

	/**
	 * 设置超时。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild timeout() {
		result.setStatusCode(DwzConstants.TIMEOUT);
		result.setMessage(DwzConstants.MSG_TIMEOUT);
		return this;
	}

	/**
	 * 设置权限限制。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild denied() {
		result.setStatusCode(DwzConstants.ERROR);
		result.setMessage(DwzConstants.MSG_DENIED);
		return this;
	}

	/**
	 * 设置系统错误。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild fail() {
		result.setStatusCode(DwzConstants.ERROR);
		result.setMessage(DwzConstants.MSG_FAIL);
		return this;
	}

	/**
	 * 关闭当前NavTab。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild closeNavTab() {
		return closeNavTab("");
	}

	/**
	 * 关闭指定NavTab。
	 * 
	 * @param navTabId
	 *            NavTab的id
	 * @return 返回构建器。
	 */
	public DwzResultBuild closeNavTab(String navTabId) {
		result.getCloseNavTab().add(navTabId);
		return this;
	}

	/**
	 * 关闭当前Dialog。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild closeDialog() {
		return closeDialog("");
	}

	/**
	 * 关闭指定Dialog。
	 * 
	 * @param dialogId
	 *            Dialog的id
	 * @return 返回构建器。
	 */
	public DwzResultBuild closeDialog(String dialogId) {
		result.getCloseDialog().add(dialogId);
		return this;
	}

	/**
	 * 重新加载当前NavTab。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadNavTab() {
		return reloadNavTab("");
	}

	/**
	 * 重新加载当前NavTab。
	 * 
	 * @param params
	 *            附带参数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadNavTab(String params) {
		return reloadNavTab(params, "");
	}

	/**
	 * 重新加载当前NavTab。
	 * 
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadNavTab(String params, String callback) {
		return reloadNavTab("", params, callback);
	}

	/**
	 * 重新加载指定NavTab。
	 * 
	 * @param navTabId
	 *            NavTab的id
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadNavTab(String navTabId, String params,
			String callback) {
		String[] args = { navTabId, "", params, callback };
		result.getReloadNavTab().add(StringUtils.join(args, ","));
		return this;
	}

	/**
	 * 跳转当前NavTab。
	 * 
	 * @param url
	 *            跳转URL
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardNavTab(String url) {
		return forwardNavTab(url, "");
	}

	/**
	 * 跳转当前NavTab。
	 * 
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardNavTab(String url, String params) {
		return forwardNavTab(url, params, "");
	}

	/**
	 * 跳转当前NavTab。
	 * 
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardNavTab(String url, String params,
			String callback) {
		return forwardNavTab("", url, params, callback);
	}

	/**
	 * 跳转指定NavTab。
	 * 
	 * @param navTabId
	 *            NavTab的id
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardNavTab(String navTabId, String url,
			String params, String callback) {
		String[] args = { navTabId, url, params, callback };
		result.getReloadNavTab().add(StringUtils.join(args, ","));
		return this;
	}

	/**
	 * 重新加载当前Dialog。
	 * 
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDialog() {
		return reloadDialog("");
	}

	/**
	 * 重新加载当前Dialog。
	 * 
	 * @param params
	 *            附带参数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDialog(String params) {
		return reloadDialog(params, "");
	}

	/**
	 * 重新加载当前Dialog。
	 * 
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDialog(String params, String callback) {
		return reloadDialog("", params, callback);
	}

	/**
	 * 重新加载指定Dialog。
	 * 
	 * @param dialogId
	 *            Dialog的id
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDialog(String dialogId, String params,
			String callback) {
		String[] args = { dialogId, "", params, callback };
		result.getReloadDialog().add(StringUtils.join(args, ","));
		return this;
	}

	/**
	 * 跳转当前Dialog。
	 * 
	 * @param url
	 *            跳转URL
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDialog(String url) {
		return forwardDialog(url, "");
	}

	/**
	 * 跳转当前Dialog。
	 * 
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDialog(String url, String params) {
		return forwardDialog(url, params, "");
	}

	/**
	 * 跳转当前Dialog。
	 * 
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDialog(String url, String params,
			String callback) {
		return forwardDialog("", url, params, callback);
	}

	/**
	 * 跳转指定Dialog。
	 * 
	 * @param dialogId
	 *            Dialog的id
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDialog(String dialogId, String url,
			String params, String callback) {
		String[] args = { dialogId, url, params, callback };
		result.getReloadDialog().add(StringUtils.join(args, ","));
		return this;
	}

	/**
	 * 重新加载指定Div。
	 * 
	 * @param divId
	 *            Div的id
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDiv(String divId) {
		return reloadDiv(divId, "");
	}

	/**
	 * 重新加载指定Div。
	 * 
	 * @param divId
	 *            Div的id
	 * @param params
	 *            附带参数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDiv(String divId, String params) {
		return reloadDiv(divId, params, "");
	}

	/**
	 * 重新加载指定Div。
	 * 
	 * @param divId
	 *            Div的id
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild reloadDiv(String divId, String params, String callback) {
		String[] args = { divId, "", params, callback };
		result.getReloadDiv().add(StringUtils.join(args, ","));
		return this;
	}

	/**
	 * 跳转指定Div。
	 * 
	 * @param divId
	 *            Div的id
	 * @param url
	 *            跳转URL
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDiv(String divId, String url) {
		return forwardDiv(divId, url, "");
	}

	/**
	 * 跳转指定Div。
	 * 
	 * @param divId
	 *            Div的id
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDiv(String divId, String url, String params) {
		return forwardDiv(divId, url, params, "");
	}

	/**
	 * 跳转指定Div。
	 * 
	 * @param divId
	 *            Div的id
	 * @param url
	 *            跳转URL
	 * @param params
	 *            附带参数
	 * @param callback
	 *            回调函数
	 * @return 返回构建器。
	 */
	public DwzResultBuild forwardDiv(String divId, String url, String params,
			String callback) {
		String[] args = { divId, url, params, callback };
		result.getReloadDiv().add(StringUtils.join(args, ","));
		return this;
	}

	/**
	 * 获取提示信息。
	 * 
	 * @param code
	 *            提示信息编码
	 * @param vars
	 *            提示信息变量
	 * @return 返回提示信息。
	 */
	private String getMessage(String code, Object... vars) {
		MessageSource messageSource = SpringUtils.getBean("messageSource");
		return messageSource.get(code, vars);
	}
}

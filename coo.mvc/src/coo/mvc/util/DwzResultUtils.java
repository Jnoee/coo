package coo.mvc.util;

import org.springframework.web.servlet.ModelAndView;

import coo.mvc.model.DwzResult;

/**
 * 基于UI框架AJAX提交后服务器响应内容（JSON）构建的页面类。
 */
public class DwzResultUtils {
	/**
	 * 提示操作成功（关闭当前页面）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView close(String message) {
		return close(message, "");
	}

	/**
	 * 提示操作成功（关闭当前页面 && 刷新指定navTab）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView close(String message, String navTabId) {
		return callback(DwzResult.close(message, navTabId));
	}

	/**
	 * 提示操作成功（刷新指定navTab && 不关闭当前页面）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView refresh(String message, String navTabId) {
		return callback(DwzResult.refresh(message, navTabId));
	}

	/**
	 * 提示操作成功（局部刷新或重新加载指定的dialog不关闭当前dialog）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param rel
	 *            待局部刷新容器的ID或待重新加载的dialog的ID
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView flush(String message, String rel) {
		return callback(DwzResult.flush(message, rel));
	}

	/**
	 * 提示操作成功（重新加载指定的dialog并关闭当前dialog）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param rel
	 *            待重新加载的dialog的ID
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView reload(String message, String rel) {
		return callback(DwzResult.reload(message, rel));
	}

	/**
	 * 提示操作成功（当前页面跳转到新页面）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView forward(String message, String url) {
		return forward(message, "", url);
	}

	/**
	 * 提示操作成功（刷新指定navTab && 使当前页面跳转到新页面）。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTabId
	 *            待刷新的navTab的ID
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView forward(String message, String navTabId,
			String url) {
		return callback(DwzResult.forward(message, navTabId, url));
	}

	/**
	 * 提示系统异常。
	 * 
	 * @return 返回系统异常的服务器响应页面。
	 */
	public static ModelAndView fail() {
		return callback(DwzResult.fail());
	}

	/**
	 * 提示操作失败。
	 * 
	 * @param message
	 *            操作失败信息
	 * @return 返回操作失败的服务器响应页面。
	 */
	public static ModelAndView fail(String message) {
		return callback(DwzResult.error(message));
	}

	/**
	 * 提示会话超时。
	 * 
	 * @return 返回会话超时的服务器响应页面。
	 */
	public static ModelAndView timeout() {
		return callback(DwzResult.timeout("会话已超时，请重新登录。"));
	}

	/**
	 * 提示页面未找到或会话已过期。
	 * 
	 * @return 返回页面未找到或会话已过期的服务器响应页面。
	 */
	public static ModelAndView expired() {
		return callback(DwzResult.timeout("页面未找到或会话已过期。"));
	}

	/**
	 * 提示权限限制。
	 * 
	 * @return 返回权限限制的服务器响应页面。
	 */
	public static ModelAndView denied() {
		return callback(DwzResult.error("您没有执行该操作的权限，请与管理员联系。"));
	}

	/**
	 * 根据结果Model返回对应的响应页面。
	 * 
	 * @param model
	 *            model
	 * @return 返回对应的响应页面。
	 */
	private static ModelAndView callback(DwzResult model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dwz-result");
		mav.addObject("ajaxResult", model);
		return mav;
	}
}

package coo.mvc.dwz;

import org.springframework.web.servlet.ModelAndView;

/**
 * dwz响应对象辅助基类。
 * 
 */
public abstract class AbstractDwzResultUtils {
	/**
	 * 提示操作成功。<br/>
	 * navTab：关闭当前navTab，页面使用navTabAjaxDone。<br/>
	 * dialog：关闭当前dialog，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView close(String message) {
		return callback(DwzResult.successClose(message, "", "", ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * navTab：重新加载当前navTab，页面使用navTabAjaxDone。<br/>
	 * dialog：重新加载当前dialog，页面使用dialogReloadDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView reload(String message) {
		return callback(DwzResult.success(message, "", "", ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * navTab：不关闭当前navTab && 重新加载指定navTab，页面使用navTabAjaxDone。<br/>
	 * dialog：不关闭当前dialog && 重新加载指定navTab，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTab
	 *            待重新加载的navTab
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView reloadNavTab(String message, String navTab) {
		return callback(DwzResult.success(message, navTab, "", ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * navTab：关闭当前navTab && 重新加载指定navTab，页面使用navTabAjaxDone。<br/>
	 * dialog：关闭当前dialog && 重新加载指定navTab，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTab
	 *            待重新加载的navTab
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeAndReloadNavTab(String message,
			String navTab) {
		return callback(DwzResult.successClose(message, navTab, "", ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * navTab：不关闭当前navTab && 重新加载指定div，页面使用navTabAjaxDone。<br/>
	 * dialog：不关闭当前dialog && 重新加载指定div，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param div
	 *            待重新加载的div
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView reloadDiv(String message, String div) {
		return callback(DwzResult.success(message, "", div, ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * navTab：关闭当前navTab && 重新加载指定div，页面使用navTabAjaxDone。<br/>
	 * dialog：关闭当前dialog && 重新加载指定div，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param div
	 *            待重新加载的div
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeAndReloadDiv(String message, String div) {
		return callback(DwzResult.successClose(message, "", div, ""));
	}

	/**
	 * 根据响应对象返回对应的响应页面。
	 * 
	 * @param ajaxResult
	 *            响应对象
	 * @return 返回对应的响应页面。
	 */
	protected static ModelAndView callback(DwzResult ajaxResult) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dwz-result");
		mav.addObject("ajaxResult", ajaxResult);
		return mav;
	}
}

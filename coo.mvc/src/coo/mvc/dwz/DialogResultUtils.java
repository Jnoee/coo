package coo.mvc.dwz;

import org.springframework.web.servlet.ModelAndView;

/**
 * Dialog页面响应对象辅助类。
 */
public class DialogResultUtils extends AbstractDwzResultUtils {
	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 重新加载当前navTab，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeAndReloadNavTab(String message) {
		return callback(DwzResult.successClose(message, "", "", ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：不关闭当前dialog && 重新加载指定dialog，页面使用dialogReloadDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待重新加载的dialog
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView reloadDialog(String message, String dialog) {
		return callback(DwzResult.success(message, "", dialog, ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 重新加载指定dialog，页面使用dialogReloadDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待重新加载的dialog
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeAndReloadDialog(String message,
			String dialog) {
		return callback(DwzResult.successClose(message, "", dialog, ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：当前dialog跳转到新页面，页面使用dialogForwardDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView forward(String message, String url) {
		return callback(DwzResult.success(message, "", "", url));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：不关闭当前dialog && 指定navTab跳转到新页面，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTab
	 *            待跳转页面的navTab
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView forwardNavTab(String message, String navTab,
			String url) {
		return callback(DwzResult.success(message, navTab, "", url));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 当前navTab跳转到新页面，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeAndForwardNavTab(String message, String url) {
		return callback(DwzResult.successClose(message, "", "", url));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 指定navTab跳转到新页面，页面使用dialogAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param navTab
	 *            待跳转页面的navTab
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeAndForwardNavTab(String message,
			String navTab, String url) {
		return callback(DwzResult.successClose(message, navTab, "", url));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 关闭指定dialog && 重新加载当前navTab，页面使用dialogCloseDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待关闭的dialog
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeDialog(String message, String dialog) {
		return callback(DwzResult.successClose(message, "", dialog, ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 关闭指定dialog && 重新加载当前navTab，页面使用dialogCloseDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待关闭的dialog
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeDialogAndReloadNavTab(String message,
			String dialog) {
		return callback(DwzResult.successClose(message, "", dialog, ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 关闭指定dialog && 重新加载指定navTab，页面使用dialogCloseDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待关闭的dialog
	 * @param navTab
	 *            待重新加载的navTab
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeDialogAndReloadNavTab(String message,
			String dialog, String navTab) {
		return callback(DwzResult.successClose(message, navTab, dialog, ""));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 关闭指定dialog && 当前navTab跳转到新页面，页面使用dialogCloseDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待关闭的dialog
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeDialogAndForwardNavTab(String message,
			String dialog, String url) {
		return callback(DwzResult.successClose(message, "", dialog, url));
	}

	/**
	 * 提示操作成功。<br/>
	 * dialog：关闭当前dialog && 关闭指定dialog && 指定navTab跳转到新页面，页面使用dialogCloseDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param dialog
	 *            待关闭的dialog
	 * @param navTab
	 *            待跳转新页面的navTab
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView closeDialogAndForwardNavTab(String message,
			String dialog, String navTab, String url) {
		return callback(DwzResult.successClose(message, navTab, dialog, url));
	}
}

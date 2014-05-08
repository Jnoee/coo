package coo.mvc.util;

import org.springframework.web.servlet.ModelAndView;

import coo.mvc.model.DwzResult;

/**
 * NavTab页面响应对象辅助类。
 */
public class NavTabResultUtils extends AbstractDwzResultUtils {
	/**
	 * 提示操作成功。<br/>
	 * navTab：当前navTab跳转到新页面，页面使用navTabAjaxDone。
	 * 
	 * 
	 * @param message
	 *            操作成功信息
	 * @param url
	 *            待跳转的页面URL
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView forward(String message, String url) {
		return callback(DwzResult.successForward(message, "", "", url));
	}

	/**
	 * 提示操作成功。<br/>
	 * navTab：当前navTab跳转到新页面 && 重新加载指定navTab，页面使用navTabAjaxDone。
	 * 
	 * @param message
	 *            操作成功信息
	 * @param url
	 *            待跳转的页面URL
	 * @param navTab
	 *            待重新加载的navTab
	 * @return 返回操作成功的服务器响应页面。
	 */
	public static ModelAndView forwardAndReloadNavTab(String message,
			String url, String navTab) {
		return callback(DwzResult.successForward(message, navTab, "", url));
	}
}
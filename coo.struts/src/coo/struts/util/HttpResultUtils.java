package coo.struts.util;

import org.apache.struts2.ServletActionContext;

import coo.struts.model.HttpResultModel;

/**
 * 一般Http请求结果的处理工具类。
 */
public class HttpResultUtils {
	/**
	 * 操作成功。
	 * 
	 * @param message
	 *            成功的提示信息
	 * @param returnUrl
	 *            待返回的页面URL
	 * @return 返回操作成功页面。
	 */
	public static String success(String message, String returnUrl) {
		HttpResultModel model = new HttpResultModel();
		model.setMessage(message);
		model.setReturnUrl(returnUrl);
		ServletActionContext.getRequest().setAttribute("httpResult", model);
		return "global-success";
	}

	/**
	 * 操作失败。
	 * 
	 * @param message
	 *            失败的提示信息
	 * @param returnUrl
	 *            待返回的页面URL
	 * @return 返回操作失败页面。
	 */
	public static String failure(String message, String returnUrl) {
		HttpResultModel model = new HttpResultModel();
		model.setMessage(message);
		model.setReturnUrl(returnUrl);
		ServletActionContext.getRequest().setAttribute("httpResult", model);
		return "global-failure";
	}
}

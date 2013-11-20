package coo.struts.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import coo.struts.util.AjaxResultUtils;
import coo.struts.util.HttpResultUtils;

/**
 * 用于处理异常错误信息的Action。
 */
public class ErrorAction extends ActionSupport {
	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 处理运行时异常。
	 * 
	 * @return 返回运行时异常页面。
	 */
	public String runtime() {
		log.error("发生运行时异常。", getException());
		if (isAjaxRequest()) {
			return AjaxResultUtils.fail();
		} else {
			return "500";
		}
	}

	/**
	 * 页面过期或未找到异常。
	 * 
	 * @return 返回页面过期或未找到异常页面。
	 */
	public String expired() {
		if (isAjaxRequest()) {
			return AjaxResultUtils.expired();
		} else {
			return "404";
		}
	}

	/**
	 * 权限限制异常。
	 * 
	 * @return 返回权限限制异常页面。
	 */
	public String denied() {
		if (isAjaxRequest()) {
			return AjaxResultUtils.denied();
		} else {
			return "403";
		}
	}

	/**
	 * 超时异常。
	 * 
	 * @return 返回超时异常页面。
	 */
	public String timeout() {
		if (isAjaxRequest()) {
			return AjaxResultUtils.timeout();
		} else {
			return "redirectToLogin";
		}
	}

	/**
	 * 一般业务操作错误异常。
	 * 
	 * @return 返回一般业务操作错误异常。
	 */
	public String failure() {
		if (getException().getCause() != null) {
			log.error("发生操作时异常。", getException());
		}
		if (isAjaxRequest()) {
			return AjaxResultUtils.fail(getException().getMessage());
		} else {
			return HttpResultUtils.failure(getException().getMessage(),
					"javascript:history.back(-1)");
		}
	}

	/**
	 * 获取页面Exception对象。
	 * 
	 * @return 返回页面Exception对象。
	 */
	private Exception getException() {
		ActionContext context = ActionContext.getContext();
		ValueStack vs = context.getValueStack();
		return (Exception) vs.findValue("exception");
	}

	/**
	 * 判断客户端请求是否是Ajax请求。
	 * 
	 * @return 如果客户端请求是Ajax请求返回true，否则返回false。
	 */
	private Boolean isAjaxRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return "XMLHttpRequest".equalsIgnoreCase(request
				.getHeader("X-Requested-With"));
	}
}

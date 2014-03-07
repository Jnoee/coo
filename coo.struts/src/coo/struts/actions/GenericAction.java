package coo.struts.actions;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import coo.core.message.MessageSource;

/**
 * 常规Action基类，实现获取Servlet的request/response/context对象的接口，使其子类Action可以直接使用这些对象。
 */
public abstract class GenericAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {
	@Resource
	protected MessageSource messageSource;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext context;

	/**
	 * 获取配置文字信息。
	 * 
	 * @param code
	 *            配置文字编码
	 * @param vars
	 *            变量
	 * @return 返回配置文字信息。
	 */
	public String getMessage(String code, Object... vars) {
		return messageSource.get(code, vars);
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}

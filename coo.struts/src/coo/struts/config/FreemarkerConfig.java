package coo.struts.config;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
 * Freemarker配置接口。
 */
public interface FreemarkerConfig {
	/**
	 * 初始化Freemarker配置。
	 * 
	 * @param config
	 *            Freemarker配置对象
	 * @param servletContext
	 *            Servlet上下文对象
	 * @throws TemplateModelException
	 *             当初始化Freemarker配置失败时抛出异常。
	 */
	void init(Configuration config, ServletContext servletContext)
			throws TemplateModelException;
}

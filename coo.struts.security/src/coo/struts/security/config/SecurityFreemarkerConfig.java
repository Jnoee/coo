package coo.struts.security.config;

import javax.servlet.ServletContext;

import coo.struts.config.FreemarkerConfig;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
 * 安全模块Freemarker配置类。
 */
public class SecurityFreemarkerConfig implements FreemarkerConfig {
	@Override
	public void init(Configuration config, ServletContext servletContext)
			throws TemplateModelException {
		config.addAutoInclude("/coo/struts/security/macros/security.ftl");
	}
}

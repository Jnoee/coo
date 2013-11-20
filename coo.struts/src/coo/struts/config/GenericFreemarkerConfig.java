package coo.struts.config;

import javax.servlet.ServletContext;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
 * 常规Freemarker配置类，设置了基础的Freemarker配置项，以及集成的UI模板配置项。
 */
public class GenericFreemarkerConfig implements FreemarkerConfig {
	@Override
	public void init(Configuration config, ServletContext servletContext)
			throws TemplateModelException {
		config.setDefaultEncoding("UTF-8");
		config.setURLEscapingCharset("UTF-8");
		config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		config.setDateFormat("yyyy-MM-dd");
		config.setTimeFormat("HH:mm:ss");
		config.setNumberFormat("#");
		config.setBooleanFormat("true,false");
		config.setClassicCompatible(true);

		config.setSharedVariable("enums", BeansWrapper.getDefaultInstance()
				.getEnumModels());
		config.setSharedVariable("statics", BeansWrapper.getDefaultInstance()
				.getStaticModels());
		config.setSharedVariable("ctx", servletContext.getContextPath());
		config.setSharedVariable("R",
				new DwzDirDefined(servletContext.getContextPath()));

		config.addAutoImport("std", "/coo/struts/macros/std.ftl");
		config.addAutoImport("dwz", "/coo/struts/macros/dwz.ftl");
	}
}

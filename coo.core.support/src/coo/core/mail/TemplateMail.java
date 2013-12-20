package coo.core.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import coo.base.exception.UncheckedException;
import coo.core.util.SpringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 模版邮件。
 */
public class TemplateMail extends Mail {
	/** 邮件模版名称 */
	private String templateName;
	/** 邮件模版数据 */
	private Map<String, Object> templateModel = new HashMap<String, Object>();

	/**
	 * 覆盖父方法，从FreeMarker模版生成邮件正文。
	 */
	@Override
	public String getText() {
		try {
			Configuration freemarkerConfiguration = SpringUtils
					.getBean("freemarkerConfiguration");
			Template template = freemarkerConfiguration
					.getTemplate(templateName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,
					templateModel);
		} catch (Exception e) {
			throw new UncheckedException("解析模版邮件时反生异常。", e);
		}
	}

	/**
	 * 设置模版变量。
	 * 
	 * @param name
	 *            变量名称
	 * @param value
	 *            变量值
	 */
	public void setVar(String name, Object value) {
		templateModel.put(name, value);
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}

package coo.mvc.security.blank.config;

import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("coo.mvc.security.blank.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		setOrder(100);
		addTemplatePath("classpath:/coo/mvc/security/blank/macros/");
		addTemplatePath("classpath:/coo/mvc/security/blank/actions/");
		addAutoImport("system", "system.ftl");
		addEnumPackage("coo.mvc.security.blank.enums");
	}
}
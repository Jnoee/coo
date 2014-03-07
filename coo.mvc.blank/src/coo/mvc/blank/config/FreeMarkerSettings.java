package coo.mvc.blank.config;

import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("coo.mvc.blank.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		setOrder(100);
		addTemplatePath("classpath:/coo/mvc/blank/actions/");
		addEnumPackage("coo.mvc.blank.enums");
	}
}
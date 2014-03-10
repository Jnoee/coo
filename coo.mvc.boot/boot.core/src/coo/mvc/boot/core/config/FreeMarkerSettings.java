package coo.mvc.boot.core.config;

import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("coo.mvc.boot.core.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		setOrder(100);
		addTemplatePath("classpath:/coo/mvc/boot/core/macros/");
		addTemplatePath("classpath:/coo/mvc/boot/core/actions/");
		addAutoImport("system", "system.ftl");
		addEnumPackage("coo.mvc.boot.core.enums");
	}
}
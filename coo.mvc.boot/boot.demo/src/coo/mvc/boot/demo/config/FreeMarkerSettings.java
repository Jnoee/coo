package coo.mvc.boot.demo.config;

import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("coo.mvc.boot.demo.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		setOrder(100);
		addTemplatePath("classpath:/coo/mvc/boot/demo/macros/");
		addTemplatePath("classpath:/coo/mvc/boot/demo/actions/");
		addEnumPackage("coo.mvc.boot.demo.enums");
	}
}
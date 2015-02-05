package coo.mvc.config;

import org.springframework.stereotype.Component;

import coo.base.util.DateUtils;
import coo.base.util.StringUtils;
import coo.mvc.constants.DwzTargetType;
import coo.mvc.constants.StatusColor;

/**
 * FreeMarker配置组件。
 */
@Component("coo.mvc.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		addTemplatePath("classpath:/coo/mvc/macros/");
		addTemplatePath("classpath:/coo/mvc/templates/");
		addAutoImport("s", "mvc.ftl");
		addAutoImport("std", "std.ftl");
		addAutoImport("dwz", "dwz.ftl");
		addAutoImport("ecs", "ecs.ftl");
		addStaticClass(StringUtils.class);
		addStaticClass(DateUtils.class);
		addStaticClass(DwzTargetType.class);
		addStaticClass(StatusColor.class);
	}
}
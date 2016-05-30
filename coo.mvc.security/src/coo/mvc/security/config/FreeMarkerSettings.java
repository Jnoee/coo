package coo.mvc.security.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("coo.mvc.security.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
  /**
   * 构造方法。
   */
  public FreeMarkerSettings() {
    addTemplatePath("classpath:/coo/mvc/security/macros/");
    addTemplatePath("classpath:/coo/mvc/security/actions/");
    addStaticClass(SecurityUtils.class);
    addAutoInclude("security.ftl");
    addAutoImport("sec", "sec.ftl");
  }
}

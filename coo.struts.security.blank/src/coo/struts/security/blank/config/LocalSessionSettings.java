package coo.struts.security.blank.config;

import org.springframework.stereotype.Component;

import coo.core.hibernate.AbstractLocalSessionSettings;

/**
 * SessionFactory设置。
 */
@Component("coo.struts.security.blank.config.LocalSessionSettings")
public class LocalSessionSettings extends AbstractLocalSessionSettings {
	/**
	 * 构造方法。
	 */
	public LocalSessionSettings() {
		addPackageToScan("coo.struts.security.blank.entity");
	}
}
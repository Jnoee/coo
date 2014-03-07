package coo.mvc.blank.config;

import org.springframework.stereotype.Component;

import coo.core.hibernate.AbstractLocalSessionSettings;

/**
 * SessionFactory设置。
 */
@Component("coo.mvc.blank.config.LocalSessionSettings")
public class LocalSessionSettings extends AbstractLocalSessionSettings {
	/**
	 * 构造方法。
	 */
	public LocalSessionSettings() {
		addPackageToScan("coo.mvc.blank.entity");
	}
}
package coo.core.security.config;

import org.springframework.stereotype.Component;

import coo.core.hibernate.AbstractLocalSessionSettings;

/**
 * SessionFactory设置。
 */
@Component("coo.core.security.config.LocalSessionSettings")
public class LocalSessionSettings extends AbstractLocalSessionSettings {
	/**
	 * 构造方法。
	 */
	public LocalSessionSettings() {
		addPackageToScan("coo.core.security.entity");
	}
}
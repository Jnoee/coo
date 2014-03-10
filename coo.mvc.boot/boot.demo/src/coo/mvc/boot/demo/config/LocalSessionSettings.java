package coo.mvc.boot.demo.config;

import org.springframework.stereotype.Component;

import coo.core.hibernate.AbstractLocalSessionSettings;

/**
 * SessionFactory设置。
 */
@Component("coo.mvc.boot.demo.config.LocalSessionSettings")
public class LocalSessionSettings extends AbstractLocalSessionSettings {
	/**
	 * 构造方法。
	 */
	public LocalSessionSettings() {
		addPackageToScan("coo.mvc.boot.demo.entity");
	}
}
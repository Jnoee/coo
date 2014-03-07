package coo.core.hibernate;

import org.springframework.stereotype.Component;

/**
 * SessionFactory设置。
 */
@Component("coo.core.hibernate.LocalSessionSettings")
public class LocalSessionSettings extends AbstractLocalSessionSettings {
	/**
	 * 构造方法。
	 */
	public LocalSessionSettings() {
		addAnnotatedPackage("coo.core.hibernate.usertype");
	}
}

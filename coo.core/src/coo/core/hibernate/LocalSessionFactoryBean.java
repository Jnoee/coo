package coo.core.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * SessionFactory工厂类。
 */
public class LocalSessionFactoryBean extends
		org.springframework.orm.hibernate4.LocalSessionFactoryBean implements
		ApplicationContextAware {
	private List<AbstractLocalSessionSettings> settings = new ArrayList<AbstractLocalSessionSettings>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		Map<String, AbstractLocalSessionSettings> localSessionSettingsMap = applicationContext
				.getBeansOfType(AbstractLocalSessionSettings.class);
		settings.addAll(localSessionSettingsMap.values());
		initAnnotatedClasses();
		initAnnotatedPackages();
		initPackagesToScan();
	}

	/**
	 * 初始化加载注解类。
	 */
	private void initAnnotatedClasses() {
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (AbstractLocalSessionSettings localSessionSettings : settings) {
			annotatedClasses.addAll(localSessionSettings.getAnnotatedClasses());
		}
		setAnnotatedClasses(annotatedClasses.toArray(new Class<?>[] {}));
	}

	/**
	 * 初始化加载注解包。
	 */
	private void initAnnotatedPackages() {
		List<String> annotatedPackages = new ArrayList<String>();
		for (AbstractLocalSessionSettings localSessionSettings : settings) {
			annotatedPackages.addAll(localSessionSettings
					.getAnnotatedPackages());
		}
		setAnnotatedPackages(annotatedPackages.toArray(new String[] {}));
	}

	/**
	 * 初始化加载扫描包。
	 */
	private void initPackagesToScan() {
		List<String> packagesToScan = new ArrayList<String>();
		for (AbstractLocalSessionSettings localSessionSettings : settings) {
			packagesToScan.addAll(localSessionSettings.getPackagesToScan());
		}
		setPackagesToScan(packagesToScan.toArray(new String[] {}));
	}
}

package coo.core.hibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * SessionFactory设置抽象类。
 */
public abstract class AbstractLocalSessionSettings {
	/** 注解类列表 */
	private List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
	/** 注解包列表 */
	private List<String> annotatedPackages = new ArrayList<String>();
	/** 扫描包列表 */
	private List<String> packagesToScan = new ArrayList<String>();

	/**
	 * 添加注解类。
	 * 
	 * @param annotatedClass
	 *            注解类
	 */
	public void addAnnotatedClass(Class<?> annotatedClass) {
		annotatedClasses.add(annotatedClass);
	}

	/**
	 * 添加注解包。
	 * 
	 * @param packageName
	 *            包名
	 */
	public void addAnnotatedPackage(String packageName) {
		annotatedPackages.add(packageName);
	}

	/**
	 * 添加扫描包。
	 * 
	 * @param packageName
	 *            包名
	 */
	public void addPackageToScan(String packageName) {
		packagesToScan.add(packageName);
	}

	public List<Class<?>> getAnnotatedClasses() {
		return annotatedClasses;
	}

	public List<String> getAnnotatedPackages() {
		return annotatedPackages;
	}

	public List<String> getPackagesToScan() {
		return packagesToScan;
	}
}

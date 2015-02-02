package coo.base.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.base.constants.Chars;
import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;

/**
 * Class工具类。
 */
public class ClassUtils {
	private static Logger log = LoggerFactory.getLogger(ClassUtils.class);

	/**
	 * 查找指定包下继承了指定类或实现了指定接口的类集合。
	 * 
	 * @param parentClass
	 *            父类或接口
	 * @param packageNames
	 *            包名
	 * @return 返回指定包下继承了指定类或实现了指定接口的类集合。
	 */
	public static List<Class<?>> findClassesByParentClass(Class<?> parentClass,
			String... packageNames) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> targetClass : findClasses(packageNames)) {
			if (!targetClass.equals(parentClass)
					&& parentClass.isAssignableFrom(targetClass)
					&& !Modifier.isAbstract(targetClass.getModifiers())) {
				classes.add(targetClass);
			}
		}
		return classes;
	}

	/**
	 * 查找指定包下标注了指定注解的类集合。
	 * 
	 * @param annotationClass
	 *            注解
	 * @param packageNames
	 *            包名
	 * @return 返回指定包下标注了指定注解的类集合。
	 */
	public static List<Class<?>> findClassesByAnnotationClass(
			Class<? extends Annotation> annotationClass, String... packageNames) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> targetClass : findClasses(packageNames)) {
			if (targetClass.isAnnotationPresent(annotationClass)) {
				classes.add(targetClass);
			}
		}
		return classes;
	}

	/**
	 * 查找指定包下的类集合。
	 * 
	 * @param packageNames
	 *            包名
	 * @return 返回指定包下的类集合。
	 */
	public static List<Class<?>> findClasses(String... packageNames) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (String className : findClassNames(packageNames)) {
			try {
				classes.add(Class.forName(className));
			} catch (Exception e) {
				log.warn("加载[" + className + "]类时发生异常。", e);
			}
		}
		return classes;
	}

	/**
	 * 查找指定包下的类名集合。
	 * 
	 * @param packageNames
	 *            包名
	 * @return 返回指定包下的类名集合。
	 */
	private static List<String> findClassNames(String... packageNames) {
		List<String> classNames = new ArrayList<String>();
		try {
			for (String packageName : packageNames) {
				String packagePath = packageName.replace(".", Chars.BACKSLASH);
				Enumeration<URL> packageUrls = Thread.currentThread()
						.getContextClassLoader().getResources(packagePath);
				while (packageUrls.hasMoreElements()) {
					URL packageUrl = packageUrls.nextElement();
					if ("jar".equals(packageUrl.getProtocol())) {
						classNames.addAll(getClassNamesFromJar(packageUrl,
								packageName));
					} else {
						classNames.addAll(getClassNamesFromDir(packageUrl,
								packageName));
					}
				}
			}
		} catch (Exception e) {
			throw new UncheckedException("获取指定包下类名集合时发生异常。", e);
		}
		return classNames;
	}

	/**
	 * 从jar包中获取指定包下的类名集合。
	 * 
	 * @param url
	 *            jar包的url
	 * @param packageName
	 *            包名
	 * @return 返回jar包中指定包下的类名集合。
	 */
	private static List<String> getClassNamesFromJar(URL url, String packageName) {
		List<String> classNames = new ArrayList<String>();
		try {
			String jarPath = URLDecoder.decode(url.toExternalForm(),
					Encoding.UTF_8);
			log.debug("开始获取[{}]中的类名...", jarPath);
			jarPath = StringUtils.substringAfter(jarPath, "jar:file:");
			jarPath = StringUtils.substringBeforeLast(jarPath, "!");
			JarFile jarInputStream = new JarFile(jarPath);
			Enumeration<JarEntry> jarEntries = jarInputStream.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();
				classNames.addAll(getClassNamesFromJar(jarEntry, packageName));
			}
			jarInputStream.close();
		} catch (Exception e) {
			log.warn("获取jar包中的类名时发生异常。", e);
		}
		return classNames;
	}

	/**
	 * 从jar包文件单元中获取指定包下的类名集合。
	 * 
	 * @param jarEntry
	 *            jar包文件单元
	 * @param packageName
	 *            包名
	 * @return 返回jar包文件单元中获取指定包下的类名集合。
	 */
	private static List<String> getClassNamesFromJar(JarEntry jarEntry,
			String packageName) {
		List<String> classNames = new ArrayList<String>();
		if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
			String className = jarEntry.getName();
			className = className.replaceFirst(".class$", "");
			className = className.replace('/', '.');
			if (className.contains(packageName)) {
				classNames.add(className);
			}
		}
		return classNames;
	}

	/**
	 * 从目录中获取指定包下的类名集合。
	 * 
	 * @param url
	 *            目录url
	 * @param packageName
	 *            包名
	 * @return 返回目录中指定包下的类名集合。
	 */
	private static List<String> getClassNamesFromDir(URL url, String packageName) {
		try {
			String dirPath = URLDecoder.decode(url.getFile(), Encoding.UTF_8);
			log.debug("开始获取[{}]中的类名...", dirPath);
			return getClassNamesFromDir(new File(dirPath), packageName);
		} catch (Exception e) {
			throw new UncheckedException("从目录中获取类名时发生异常。", e);
		}
	}

	/**
	 * 从目录中获取指定包下的类名集合。
	 * 
	 * @param dir
	 *            目录
	 * @param packageName
	 *            包名
	 * @return 返回目录中指定包下的类名集合。
	 */
	private static List<String> getClassNamesFromDir(File dir,
			String packageName) {
		List<String> classNames = new ArrayList<String>();
		try {
			File[] files = dir.listFiles();
			String separator = System.getProperty("file.separator");
			for (File file : files) {
				if (file.isDirectory()) {
					classNames.addAll(getClassNamesFromDir(file, packageName
							+ "." + file.getName()));
				} else if (file.getName().endsWith(".class")) {
					String className = file.getPath();
					className = className.replace(separator, ".");
					className = packageName
							+ StringUtils.substringAfterLast(className,
									packageName);
					className = className.replaceFirst(".class$", "");
					classNames.add(className);
				}
			}
		} catch (Exception e) {
			log.warn("获取目录中的类名时发生异常。", e);
		}
		return classNames;
	}
}

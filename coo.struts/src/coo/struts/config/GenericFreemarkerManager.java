package coo.struts.config;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.base.exception.UncheckedException;
import coo.base.util.ClassUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 常规的Freemarker管理器，该类集成strut2中的FreemarkerManager实现自己的Freemarker配置管理。
 */
public class GenericFreemarkerManager extends FreemarkerManager {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final static String PACKAGE_NAME = "coo.struts";

	@Override
	public void init(ServletContext servletContext) throws TemplateException {
		super.init(servletContext);

		List<Class<?>> configClasses = ClassUtils.findClassesByParentClass(
				FreemarkerConfig.class, PACKAGE_NAME);
		for (Class<?> configClass : configClasses) {
			invokeConfigInit(configClass, servletContext);
		}
	}

	/**
	 * 执行配置初始化动作。
	 * 
	 * @param configClass
	 *            实现了FreemarkerConfig接口的类
	 * @param servletContext
	 *            Servlet上下文对象
	 */
	private void invokeConfigInit(Class<?> configClass,
			ServletContext servletContext) {
		try {
			Object configObject = configClass.newInstance();
			Method method = configClass.getMethod("init", Configuration.class,
					ServletContext.class);
			method.invoke(configObject, config, servletContext);
			log.debug("加载[{}]配置完成。", configClass);
		} catch (Exception e) {
			throw new UncheckedException("加载Freemarker配置时发生异常。", e);
		}
	}
}

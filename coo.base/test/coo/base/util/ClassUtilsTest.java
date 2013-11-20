package coo.base.util;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtilsTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testFindClasses() throws Exception {
		List<Class<?>> classes = ClassUtils.findClasses("coo");
		log.debug("找到 " + classes.size() + " 个目标类：");
		for (Class<?> clazz : classes) {
			log.debug(clazz.getName());
		}
	}

	@Test
	public void testFindClassesByParentClass() throws Exception {
		List<Class<?>> classes = ClassUtils.findClassesByParentClass(
				ServletRequest.class, "javax.servlet");
		log.debug("找到 " + classes.size() + " 个目标类：");
		for (Class<?> clazz : classes) {
			log.debug(clazz.getName());
		}
	}

	@Test
	public void testFindClassesByAnnotationClass() throws Exception {
		List<Class<?>> classes = ClassUtils.findClassesByAnnotationClass(
				Resource.class, "javax.servlet");
		log.debug("找到 " + classes.size() + " 个目标类：");
		for (Class<?> clazz : classes) {
			log.debug(clazz.getName());
		}
	}
}

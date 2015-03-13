package coo.core.hibernate.cache;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import coo.core.hibernate.EntityClassBeanFactoryPostProcessor;

/**
 * 实体缓存管理组件。
 */
@Component
public class EntityCacheManager extends EntityClassBeanFactoryPostProcessor {
	private List<Class<?>> cachedEntityClasses = new ArrayList<Class<?>>();

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) {
		super.postProcessBeanFactory(beanFactory);
		for (Class<?> entityClass : entityClasses) {
			if (entityClass.isAnnotationPresent(Cache.class)) {
				cachedEntityClasses.add(entityClass);
			}
		}
	}

	/**
	 * 清空指定实体类的缓存。
	 * 
	 * @param entityClasses
	 *            实体类列表
	 */
	public void evictEntityRegion(Class<?>... entityClasses) {
		for (Class<?> entityClass : entityClasses) {
			sessionFactory.getCache().evictEntityRegion(entityClass);
		}
	}

	public List<Class<?>> getCachedEntityClasses() {
		return cachedEntityClasses;
	}
}

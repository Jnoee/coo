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
	 * 清空指定实体缓存。
	 * 
	 * @param entityClasses
	 *            实体类列表
	 */
	public void evictEntityRegion(Class<?>... entityClasses) {
		for (Class<?> entityClass : entityClasses) {
			sessionFactory.getCache().evictEntityRegion(entityClass);
		}
	}

	/**
	 * 清空所有实体缓存。
	 */
	public void evictEntityRegions() {
		sessionFactory.getCache().evictEntityRegions();
	}

	/**
	 * 清空所有集合缓存。
	 */
	public void evictCollectionRegions() {
		sessionFactory.getCache().evictCollectionRegions();
	}

	/**
	 * 清空所有查询缓存。
	 */
	public void evictQueryRegions() {
		sessionFactory.getCache().evictQueryRegions();
	}

	/**
	 * 清空所有缓存。
	 */
	public void evictAllRegions() {
		sessionFactory.getCache().evictAllRegions();
	}

	public List<Class<?>> getCachedEntityClasses() {
		return cachedEntityClasses;
	}
}

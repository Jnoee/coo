package coo.core.hibernate.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.stereotype.Component;

/**
 * 缓存管理组件。
 */
@Component
public class HiCacheManager {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 获取所有缓存名称。
	 * 
	 * @return 缓存名称列表。
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllCacheEntity() {
		List<String> cacheNames = new ArrayList<>();
		for (Map.Entry<String, ClassMetadata> entry : sessionFactory
				.getAllClassMetadata().entrySet()) {
			if (entry.getValue().getMappedClass()
					.isAnnotationPresent(Cache.class)) {
				cacheNames.add(entry.getKey());
			}
		}
		return cacheNames;
	}

	/**
	 * 清空指定名称下的缓存。
	 * 
	 * @param regions
	 *            名称列表。
	 */
	public void evictEntityRegion(String[] regions) {
		org.hibernate.Cache cache = sessionFactory.getCache();
		for (String region : regions) {
			cache.evictEntityRegion(region);
		}
	}
}

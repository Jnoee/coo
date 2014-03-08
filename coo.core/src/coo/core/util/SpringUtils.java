package coo.core.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import coo.base.exception.UncheckedException;
import coo.base.util.StringUtils;
import coo.core.model.UuidEntity;

/**
 * Spring工具类。
 */
@Component
@Lazy(value = false)
public class SpringUtils implements ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) {
		SpringUtils.context = context;
	}

	/**
	 * 获取Spring容器的应用上下文。
	 * 
	 * @return 返回Spring容器的应用上下文。
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * 从Spring容器中获取指定名称的Bean。
	 * 
	 * @param <T>
	 *            Bean类型
	 * @param beanName
	 *            bean名称
	 * @return 返回指定名称的Bean。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}

	/**
	 * 获取UuidEntity对象。
	 * 
	 * @param <T>
	 *            实体类型
	 * 
	 * @param entityClass
	 *            实体类
	 * @param id
	 *            实体ID
	 * @return 返回对应的UuidEntity对象。
	 */
	@SuppressWarnings("unchecked")
	public static <T extends UuidEntity> T getUuidEntityObject(
			Class<T> entityClass, String id) {
		SessionFactory sessionFactory = getBean("sessionFactory");
		return (T) sessionFactory.getCurrentSession().get(entityClass, id);
	}

	/**
	 * 根据通配符资源路径获取资源列表。
	 * 
	 * @param wildcardResourcePaths
	 *            通配符资源路径
	 * @return 返回资源列表。
	 */
	public static List<Resource> getResourcesByWildcard(
			String... wildcardResourcePaths) {
		List<Resource> resources = new ArrayList<Resource>();
		try {
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			for (String basename : wildcardResourcePaths) {
				for (Resource resource : resourcePatternResolver
						.getResources(basename)) {
					resources.add(resource);
				}
			}
		} catch (Exception e) {
			throw new UncheckedException("获取资源列表时反生异常。", e);
		}
		return resources;
	}

	/**
	 * 根据通配符资源路径获取资源路径列表。
	 * 
	 * @param resourceDir
	 *            资源目录
	 * @param wildcardResourcePaths
	 *            通配符资源路径
	 * @return 返回实际匹配的资源路径列表。
	 */
	public static List<String> getResourcePathsByWildcard(String resourceDir,
			String... wildcardResourcePaths) {
		List<String> resourcePaths = new ArrayList<String>();
		try {
			for (Resource resource : getResourcesByWildcard(wildcardResourcePaths)) {
				String uri = resource.getURI().toString();
				if (resource instanceof FileSystemResource
						|| resource instanceof UrlResource) {
					String resourcePath = "classpath:" + resourceDir
							+ StringUtils.substringAfter(uri, resourceDir);
					resourcePaths.add(resourcePath);
				}
			}
		} catch (Exception e) {
			throw new UncheckedException("获取资源文件时发生异常。", e);
		}
		return resourcePaths;
	}

	/**
	 * 根据通配符资源路径获取资源基本名称列表。
	 * 
	 * @param resourceDir
	 *            资源目录
	 * @param wildcardResourcePaths
	 *            通配符资源路径
	 * @return 返回实际匹配的资源基本名称列表。
	 */
	public static List<String> getResourceBasenamesByWildcard(
			String resourceDir, String... wildcardResourcePaths) {
		List<String> resourceBasenames = new ArrayList<String>();
		for (String resourcePath : getResourcePathsByWildcard(resourceDir,
				wildcardResourcePaths)) {
			resourceBasenames.add(StringUtils.substringBeforeLast(resourcePath,
					"."));
		}
		return resourceBasenames;
	}
}

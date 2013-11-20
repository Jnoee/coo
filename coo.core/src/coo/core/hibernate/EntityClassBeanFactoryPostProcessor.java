package coo.core.hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.util.ClassUtils;

import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.CollectionUtils;

/**
 * 自动获取sessionFactory配置的待扫描包路径中的业务实体类的BeanFactoryPostProcessor。
 */
public abstract class EntityClassBeanFactoryPostProcessor implements
		BeanFactoryPostProcessor {
	protected LocalSessionFactoryBean localSessionFactoryBean;
	protected SessionFactory sessionFactory;
	protected List<Class<?>> entityClasses = new ArrayList<Class<?>>();

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) {
		if (beanFactory.containsBeanDefinition("sessionFactory")) {
			localSessionFactoryBean = (LocalSessionFactoryBean) beanFactory
					.getBean("&sessionFactory");
			sessionFactory = (SessionFactory) beanFactory
					.getBean("sessionFactory");
			String[] packagesToScan = (String[]) BeanUtils.getField(
					localSessionFactoryBean, "packagesToScan");
			if (!CollectionUtils.isEmpty(packagesToScan)) {
				for (String packageToScan : packagesToScan) {
					entityClasses.addAll(getEntityClasses(packageToScan));
				}
			}
		}
	}

	/**
	 * 从指定的包路径获取业务实体类集合。
	 * 
	 * @param packageToScan
	 *            待扫描的包路径
	 * @return 返回业务实体类集合。
	 */
	private List<Class<?>> getEntityClasses(String packageToScan) {
		List<Class<?>> entityClasses = new ArrayList<Class<?>>();
		try {
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(packageToScan)
					+ "/**/*.class";
			Resource[] resources = resourcePatternResolver
					.getResources(pattern);
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
					resourcePatternResolver);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader reader = readerFactory
							.getMetadataReader(resource);
					String className = reader.getClassMetadata().getClassName();
					if (matchesFilter(reader, readerFactory)) {
						entityClasses.add(resourcePatternResolver
								.getClassLoader().loadClass(className));
					}
				}
			}
		} catch (Exception e) {
			throw new UncheckedException("从 " + packageToScan
					+ " 中扫描获取业务实体类时发生异常", e);
		}
		return entityClasses;
	}

	/**
	 * 是否匹配过滤器。
	 * 
	 * @param reader
	 *            元数据读取器
	 * @param readerFactory
	 *            元数据读取器工厂
	 * @return 返回是否匹配过滤器。
	 * @throws IOException
	 *             比较匹配发生异常时抛出
	 */
	private boolean matchesFilter(MetadataReader reader,
			MetadataReaderFactory readerFactory) throws IOException {
		TypeFilter[] entityTypeFilters = new TypeFilter[] { new AnnotationTypeFilter(
				Entity.class, false) };
		if (entityTypeFilters != null) {
			for (TypeFilter filter : entityTypeFilters) {
				if (filter.match(reader, readerFactory)) {
					return true;
				}
			}
		}
		return false;
	}
}

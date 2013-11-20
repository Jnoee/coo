package coo.core.hibernate.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import coo.base.util.CollectionUtils;
import coo.core.hibernate.EntityClassBeanFactoryPostProcessor;

/**
 * 该类定义了一个Dao注册器组件。它实现了BeanFactoryPostProcessor接口，
 * 在Spring容器初始化完成后自动根据sessionFactory的packagesToScan指定的路径查找业务实体类
 * ，并为这些业务实体类注册相应的泛型Dao组件。这样就省去了在配置文件中逐个为业务实体声明对应的Dao组件。
 */
@Component
public class DaoRegister extends EntityClassBeanFactoryPostProcessor {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) {
		super.postProcessBeanFactory(beanFactory);
		if (CollectionUtils.isNotEmpty(entityClasses)) {
			for (Class<?> entityClass : entityClasses) {
				AnnotatedGenericBeanDefinition daoDefinition = new AnnotatedGenericBeanDefinition(
						Dao.class);

				ConstructorArgumentValues av = new ConstructorArgumentValues();
				av.addGenericArgumentValue(entityClass);
				daoDefinition.setConstructorArgumentValues(av);

				String beanName = entityClass.getSimpleName();
				char[] chars = beanName.toCharArray();
				chars[0] = Character.toLowerCase(chars[0]);
				beanName = new String(chars) + "Dao";
				((DefaultListableBeanFactory) beanFactory)
						.registerBeanDefinition(beanName, daoDefinition);
				log.debug("自动注入DAO组件: " + beanName);
			}
		}
	}
}
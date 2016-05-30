package coo.core.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 自动获取sessionFactory配置的待扫描包路径中的业务实体类的BeanFactoryPostProcessor。
 */
public abstract class EntityClassBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  protected SessionFactory sessionFactory;
  protected List<Class<?>> entityClasses = new ArrayList<Class<?>>();

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    if (beanFactory.containsBeanDefinition("sessionFactory")) {
      sessionFactory = (SessionFactory) beanFactory.getBean("sessionFactory");
      Map<String, ClassMetadata> metadatas = sessionFactory.getAllClassMetadata();
      for (String metadataName : metadatas.keySet()) {
        entityClasses.add(metadatas.get(metadataName).getMappedClass());
      }
    }
  }
}

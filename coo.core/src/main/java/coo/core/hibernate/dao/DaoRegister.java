package coo.core.hibernate.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 该类定义了一个Dao注册器组件。它实现了BeanFactoryPostProcessor接口，
 * 在Spring容器初始化完成后自动根据sessionFactory的packagesToScan指定的路径查找业务实体类
 * ，并为这些业务实体类注册相应的泛型Dao组件。这样就省去了在配置文件中逐个为业务实体声明对应的Dao组件。
 */
public class DaoRegister implements BeanFactoryAware {
  private final Logger log = LoggerFactory.getLogger(getClass());
  @Resource
  private SessionFactory sessionFactory;

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    Map<String, ClassMetadata> metadatas = sessionFactory.getAllClassMetadata();
    for (Map.Entry<String, ClassMetadata> entry : metadatas.entrySet()) {
      Class<?> entityClass = metadatas.get(entry.getKey()).getMappedClass();
      AnnotatedGenericBeanDefinition daoDefinition = new AnnotatedGenericBeanDefinition(Dao.class);

      ConstructorArgumentValues av = new ConstructorArgumentValues();
      av.addGenericArgumentValue(entityClass);
      daoDefinition.setConstructorArgumentValues(av);

      String beanName = entityClass.getSimpleName();
      char[] chars = beanName.toCharArray();
      chars[0] = Character.toLowerCase(chars[0]);
      beanName = new String(chars) + "Dao";
      ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(beanName, daoDefinition);
      log.debug("自动注入DAO组件[{}]", beanName);
    }
  }
}

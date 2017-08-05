package coo.core.hibernate.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.metadata.ClassMetadata;

/**
 * 实体缓存管理组件。
 */
public class EntityCacheManager {
  private List<Class<?>> cachedEntityClasses = new ArrayList<>();
  @Resource
  private SessionFactory sessionFactory;

  /**
   * 初始化方法。
   */
  @PostConstruct
  public void init() {
    Map<String, ClassMetadata> metadatas = sessionFactory.getAllClassMetadata();
    for (Map.Entry<String, ClassMetadata> entry : metadatas.entrySet()) {
      Class<?> entityClass = metadatas.get(entry.getKey()).getMappedClass();
      if (entityClass.isAnnotationPresent(Cache.class)) {
        cachedEntityClasses.add(entityClass);
      }
    }
  }

  /**
   * 清空指定实体缓存。
   * 
   * @param entityClasses 实体类列表
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

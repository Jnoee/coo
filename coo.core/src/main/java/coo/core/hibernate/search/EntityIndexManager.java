package coo.core.hibernate.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.CacheMode;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.base.exception.UncheckedException;

/**
 * 实体全文索引管理组件。
 */
public class EntityIndexManager {
  private final Logger log = LoggerFactory.getLogger(getClass());
  @Resource
  private SessionFactory sessionFactory;
  private List<Class<?>> indexedEntityClasses = new ArrayList<>();

  /**
   * 初始化方法。
   */
  @PostConstruct
  public void init() {
    Map<String, ClassMetadata> metadatas = sessionFactory.getAllClassMetadata();
    for (Map.Entry<String, ClassMetadata> entry : metadatas.entrySet()) {
      Class<?> entityClass = metadatas.get(entry.getKey()).getMappedClass();
      if (entityClass.isAnnotationPresent(Indexed.class)) {
        indexedEntityClasses.add(entityClass);
      }
    }
  }

  /**
   * 同步创建指定实体类的全文索引，如果未指定实体类则创建全部实体类的全文索引。
   * 
   * @param entityClasses 实体类列表
   */
  public void startAndWait(Class<?>... entityClasses) {
    try {
      log.info("开始重建索引...");
      createIndexer(entityClasses).startAndWait();
      log.info("完成重建索引。");
    } catch (Exception e) {
      throw new UncheckedException("重建全文索引时发生异常。", e);
    }
  }

  /**
   * 异步创建指定实体类的全文索引。
   * 
   * @param entityClasses 实体类列表
   */
  public void start(Class<?>... entityClasses) {
    try {
      createIndexer(entityClasses).start();
    } catch (Exception e) {
      throw new UncheckedException("重建全文索引时发生异常。", e);
    }
  }

  /**
   * 创建索引构建组件。
   * 
   * @param entityClasses 业务实体类
   * @return 返回索引构建组件。
   */
  private MassIndexer createIndexer(Class<?>... entityClasses) {
    FullTextSession session = Search.getFullTextSession(sessionFactory.getCurrentSession());
    return session.createIndexer(entityClasses).typesToIndexInParallel(2).threadsToLoadObjects(20)
        .batchSizeToLoadObjects(25).cacheMode(CacheMode.NORMAL);
  }

  public List<Class<?>> getIndexedEntityClasses() {
    return indexedEntityClasses;
  }
}

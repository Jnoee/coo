package coo.core.hibernate.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import coo.base.exception.UncheckedException;
import coo.core.hibernate.EntityClassBeanFactoryPostProcessor;

/**
 * 全文索引组件。
 */
@Component
public class FullTextIndexer extends EntityClassBeanFactoryPostProcessor {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final ReentrantLock lock = new ReentrantLock();
	private FullTextSession session;
	private List<Class<?>> indexedEntityClasses = new ArrayList<Class<?>>();

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) {
		super.postProcessBeanFactory(beanFactory);
		for (Class<?> entityClass : entityClasses) {
			if (entityClass.isAnnotationPresent(Indexed.class)) {
				indexedEntityClasses.add(entityClass);
			}
		}
		session = Search.getFullTextSession(sessionFactory.openSession());
	}

	/**
	 * 同步创建指定实体类的全文索引，如果未指定实体类则创建全部实体类的全文索引。
	 * 
	 * @param entityClasses
	 *            实体类列表
	 */
	public void startAndWait(Class<?>... entityClasses) {
		lock.lock();
		if (entityClasses.length == 0) {
			entityClasses = indexedEntityClasses.toArray(new Class<?>[] {});
		}
		try {
			for (Class<?> indexedEntityClass : entityClasses) {
				log.info("开始重建 " + indexedEntityClass.getSimpleName()
						+ " 全文索引...");
				Long startTime = System.currentTimeMillis();
				session.createIndexer(indexedEntityClass).startAndWait();
				Long endTime = System.currentTimeMillis();
				log.info("完成重建 " + indexedEntityClass.getSimpleName()
						+ " 全文索引...耗时" + (endTime - startTime) + "毫秒。");
			}
		} catch (Exception e) {
			throw new UncheckedException("重建全文索引时发生异常。", e);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 异步创建指定实体类的全文索引。
	 * 
	 * @param entityClasses
	 *            实体类列表
	 */
	public void start(Class<?>... entityClasses) {
		lock.lock();
		if (entityClasses.length == 0) {
			entityClasses = indexedEntityClasses.toArray(new Class<?>[] {});
		}
		try {
			for (Class<?> indexedEntityClass : entityClasses) {
				log.info("异步重建 " + indexedEntityClass.getSimpleName()
						+ " 全文索引。");
				session.createIndexer(indexedEntityClass).start();
			}
		} catch (Exception e) {
			throw new UncheckedException("重建全文索引时发生异常。", e);
		} finally {
			lock.unlock();
		}
	}

	public List<Class<?>> getIndexedEntityClasses() {
		return indexedEntityClasses;
	}
}
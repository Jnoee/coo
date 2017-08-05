package coo.core.config;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

import coo.base.exception.UncheckedException;
import coo.core.hibernate.GenericLocalSessionFactoryBean;
import coo.core.hibernate.cache.EntityCacheManager;
import coo.core.hibernate.dao.DaoRegister;
import coo.core.hibernate.search.EntityIndexManager;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties({ExecutorProperties.class, DruidProperties.class,
    HibernateProperties.class})
@ComponentScan("coo.core")
public class CoreConfiguration {
  @Resource
  private ExecutorProperties executorProperties;
  @Resource
  private DruidProperties druidProperties;
  @Resource
  private HibernateProperties hibernateProperties;

  @Bean(destroyMethod = "close")
  public DruidDataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(druidProperties.getUrl());
    dataSource.setUsername(druidProperties.getUsername());
    dataSource.setPassword(druidProperties.getPassword());
    dataSource.setInitialSize(druidProperties.getInitialSize());
    dataSource.setMaxActive(druidProperties.getMaxActive());
    dataSource.setMinIdle(druidProperties.getMinIdle());
    dataSource.setMaxWait(druidProperties.getMaxWait());
    dataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
    dataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
    dataSource.setValidationQuery(druidProperties.getValidationQuery());
    dataSource.setTestWhileIdle(druidProperties.getTestWhileIdle());
    dataSource.setTestOnBorrow(druidProperties.getTestOnBorrow());
    dataSource.setTestOnReturn(druidProperties.getTestOnReturn());
    dataSource.setPoolPreparedStatements(druidProperties.getPoolPreparedStatements());
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(
        druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
    dataSource.setRemoveAbandoned(druidProperties.getRemoveAbandoned());
    dataSource.setRemoveAbandonedTimeout(druidProperties.getRemoveAbandonedTimeout());
    dataSource.setLogAbandoned(druidProperties.getLogAbandoned());
    try {
      dataSource.setFilters(druidProperties.getFilters());
    } catch (SQLException e) {
      throw new UncheckedException("配置数据库连接池时发生异常。", e);
    }
    return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    GenericLocalSessionFactoryBean sessionFactory = new GenericLocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setHibernateProperties(hibernateProperties.toProperties());
    return sessionFactory;
  }

  @Bean
  @ConditionalOnBean(SessionFactory.class)
  public PlatformTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }

  @Bean
  public EntityCacheManager entityCacheManager() {
    return new EntityCacheManager();
  }

  @Bean
  public static EntityIndexManager entityIndexManager() {
    return new EntityIndexManager();
  }

  @Bean
  public static DaoRegister daoRegister() {
    return new DaoRegister();
  }

  @Bean
  public ExecutorService taskExecutor() {
    return Executors.newFixedThreadPool(executorProperties.getExecutorPoolSize());
  }

  @Bean
  public ExecutorService taskScheduler() {
    return Executors.newScheduledThreadPool(executorProperties.getSchedulerPoolSize());
  }
}

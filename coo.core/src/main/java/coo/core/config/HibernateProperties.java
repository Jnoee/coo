package coo.core.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Hibernate配置属性。
 */
@ConfigurationProperties("coo.hibernate")
public class HibernateProperties {
  /** 数据库适配器 */
  private String dialect = "org.hibernate.dialect.H2Dialect";
  /** 是否在控制台打印SQL语句 */
  private String showSql = "false";
  /** 二级缓存设置 */
  private Cache cache = new Cache();
  /** JDBC设置 */
  private Jdbc jdbc = new Jdbc();
  /** 全文索引设置 */
  private Search search = new Search();

  /**
   * 转换为Properties。
   * 
   * @return 返回Properties。
   */
  public Properties toProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", dialect);
    properties.setProperty("hibernate.show_sql", showSql);
    properties.setProperty("hibernate.cache.use_query_cache", cache.useQueryCache);
    properties.setProperty("hibernate.cache.use_second_level_cache", cache.useSecondLevelCache);
    properties.setProperty("hibernate.cache.region.factory_class", cache.regionFactoryClass);
    properties.setProperty("hibernate.jdbc.fetch_size", jdbc.fetchSize);
    properties.setProperty("hibernate.jdbc.batch_size", jdbc.batchSize);
    properties.setProperty("hibernate.search.default.exclusive_index_use",
        search.defaultExclusiveIndexUse);
    properties.setProperty("hibernate.search.default.directory_provider",
        search.defaultDirectoryProvider);
    properties.setProperty("hibernate.search.infinispan.configuration_resourcename",
        search.infinispanConfigurationResourcename);
    properties.setProperty("hibernate.search.default.worker.execution",
        search.defaultWorkerExecution);
    properties.setProperty("hibernate.search.default.worker.backend", search.defaultWorkerBackend);
    properties.setProperty("hibernate.search.default.indexBase", search.defaultIndexBase);
    properties.setProperty("hibernate.search.default.writeLockTimeout",
        search.defaultWriteLockTimeout);
    properties.setProperty("hibernate.search.analyzer", search.analyzer);
    return properties;
  }

  public String getDialect() {
    return dialect;
  }

  public void setDialect(String dialect) {
    this.dialect = dialect;
  }

  public String getShowSql() {
    return showSql;
  }

  public void setShowSql(String showSql) {
    this.showSql = showSql;
  }

  public Cache getCache() {
    return cache;
  }

  public void setCache(Cache cache) {
    this.cache = cache;
  }

  public Jdbc getJdbc() {
    return jdbc;
  }

  public void setJdbc(Jdbc jdbc) {
    this.jdbc = jdbc;
  }

  public Search getSearch() {
    return search;
  }

  public void setSearch(Search search) {
    this.search = search;
  }

  /**
   * 缓存配置。
   */
  public static class Cache {
    private String useQueryCache = "false";
    private String useSecondLevelCache = "true";
    private String regionFactoryClass = "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory";

    public String getUseQueryCache() {
      return useQueryCache;
    }

    public void setUseQueryCache(String useQueryCache) {
      this.useQueryCache = useQueryCache;
    }

    public String getUseSecondLevelCache() {
      return useSecondLevelCache;
    }

    public void setUseSecondLevelCache(String useSecondLevelCache) {
      this.useSecondLevelCache = useSecondLevelCache;
    }

    public String getRegionFactoryClass() {
      return regionFactoryClass;
    }

    public void setRegionFactoryClass(String regionFactoryClass) {
      this.regionFactoryClass = regionFactoryClass;
    }
  }

  /**
   * Jdbc配置。
   */
  public static class Jdbc {
    private String fetchSize = "50";
    private String batchSize = "25";

    public String getFetchSize() {
      return fetchSize;
    }

    public void setFetchSize(String fetchSize) {
      this.fetchSize = fetchSize;
    }

    public String getBatchSize() {
      return batchSize;
    }

    public void setBatchSize(String batchSize) {
      this.batchSize = batchSize;
    }
  }

  /**
   * 全文索引配置。
   */
  public static class Search {
    private String defaultExclusiveIndexUse = "false";
    private String defaultDirectoryProvider = "filesystem";
    private String infinispanConfigurationResourcename = "infinispan.xml";
    private String defaultWorkerExecution = "sync";
    private String defaultWorkerBackend = "";
    private String defaultIndexBase = "${user.home}/.coo/indexed";
    private String defaultWriteLockTimeout = "1000";
    private String analyzer = "org.apache.lucene.analysis.standard.StandardAnalyzer";

    public String getDefaultExclusiveIndexUse() {
      return defaultExclusiveIndexUse;
    }

    public void setDefaultExclusiveIndexUse(String defaultExclusiveIndexUse) {
      this.defaultExclusiveIndexUse = defaultExclusiveIndexUse;
    }

    public String getDefaultDirectoryProvider() {
      return defaultDirectoryProvider;
    }

    public void setDefaultDirectoryProvider(String defaultDirectoryProvider) {
      this.defaultDirectoryProvider = defaultDirectoryProvider;
    }

    public String getInfinispanConfigurationResourcename() {
      return infinispanConfigurationResourcename;
    }

    public void setInfinispanConfigurationResourcename(String infinispanConfigurationResourcename) {
      this.infinispanConfigurationResourcename = infinispanConfigurationResourcename;
    }

    public String getDefaultWorkerExecution() {
      return defaultWorkerExecution;
    }

    public void setDefaultWorkerExecution(String defaultWorkerExecution) {
      this.defaultWorkerExecution = defaultWorkerExecution;
    }

    public String getDefaultWorkerBackend() {
      return defaultWorkerBackend;
    }

    public void setDefaultWorkerBackend(String defaultWorkerBackend) {
      this.defaultWorkerBackend = defaultWorkerBackend;
    }

    public String getDefaultIndexBase() {
      return defaultIndexBase;
    }

    public void setDefaultIndexBase(String defaultIndexBase) {
      this.defaultIndexBase = defaultIndexBase;
    }

    public String getDefaultWriteLockTimeout() {
      return defaultWriteLockTimeout;
    }

    public void setDefaultWriteLockTimeout(String defaultWriteLockTimeout) {
      this.defaultWriteLockTimeout = defaultWriteLockTimeout;
    }

    public String getAnalyzer() {
      return analyzer;
    }

    public void setAnalyzer(String analyzer) {
      this.analyzer = analyzer;
    }
  }
}

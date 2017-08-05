package coo.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("coo.druid")
public class DruidProperties {
  private String url = "jdbc:h2:mem:coo";
  private String username = "su";
  private String password;
  /** 初始、最大、最小连接数 */
  private Integer initialSize = 10;
  private Integer maxActive = 100;
  private Integer minIdle = 10;
  /** 获取连接等待超时的时间 */
  private Integer maxWait = 60000;
  /** 间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
  private Integer timeBetweenEvictionRunsMillis = 60000;
  /** 连接在池中最小生存的时间，单位是毫秒 */
  private Integer minEvictableIdleTimeMillis = 300000;
  private String validationQuery = "SELECT 'x'";
  private Boolean testWhileIdle = true;
  private Boolean testOnBorrow = true;
  private Boolean testOnReturn = false;
  /** 打开PSCache，并且指定每个连接上PSCache的大小 */
  private Boolean poolPreparedStatements = false;
  private Integer maxPoolPreparedStatementPerConnectionSize = 0;
  /** 配置监控统计拦截的filters */
  private String filters = "slf4j";
  /** 启用自动关闭长时间不使用连接的功能（怀疑存在连接泄漏时启用） */
  private Boolean removeAbandoned = false;
  /** 自动关闭超过1800秒未关闭的连接 */
  private Integer removeAbandonedTimeout = 1800;
  /** 关闭abanded连接时输出错误日志 */
  private Boolean logAbandoned = true;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getInitialSize() {
    return initialSize;
  }

  public void setInitialSize(Integer initialSize) {
    this.initialSize = initialSize;
  }

  public Integer getMaxActive() {
    return maxActive;
  }

  public void setMaxActive(Integer maxActive) {
    this.maxActive = maxActive;
  }

  public Integer getMinIdle() {
    return minIdle;
  }

  public void setMinIdle(Integer minIdle) {
    this.minIdle = minIdle;
  }

  public Integer getMaxWait() {
    return maxWait;
  }

  public void setMaxWait(Integer maxWait) {
    this.maxWait = maxWait;
  }

  public Integer getTimeBetweenEvictionRunsMillis() {
    return timeBetweenEvictionRunsMillis;
  }

  public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
    this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
  }

  public Integer getMinEvictableIdleTimeMillis() {
    return minEvictableIdleTimeMillis;
  }

  public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
    this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
  }

  public String getValidationQuery() {
    return validationQuery;
  }

  public void setValidationQuery(String validationQuery) {
    this.validationQuery = validationQuery;
  }

  public Boolean getTestWhileIdle() {
    return testWhileIdle;
  }

  public void setTestWhileIdle(Boolean testWhileIdle) {
    this.testWhileIdle = testWhileIdle;
  }

  public Boolean getTestOnBorrow() {
    return testOnBorrow;
  }

  public void setTestOnBorrow(Boolean testOnBorrow) {
    this.testOnBorrow = testOnBorrow;
  }

  public Boolean getTestOnReturn() {
    return testOnReturn;
  }

  public void setTestOnReturn(Boolean testOnReturn) {
    this.testOnReturn = testOnReturn;
  }

  public Boolean getPoolPreparedStatements() {
    return poolPreparedStatements;
  }

  public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
    this.poolPreparedStatements = poolPreparedStatements;
  }

  public Integer getMaxPoolPreparedStatementPerConnectionSize() {
    return maxPoolPreparedStatementPerConnectionSize;
  }

  public void setMaxPoolPreparedStatementPerConnectionSize(
      Integer maxPoolPreparedStatementPerConnectionSize) {
    this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
  }

  public String getFilters() {
    return filters;
  }

  public void setFilters(String filters) {
    this.filters = filters;
  }

  public Boolean getRemoveAbandoned() {
    return removeAbandoned;
  }

  public void setRemoveAbandoned(Boolean removeAbandoned) {
    this.removeAbandoned = removeAbandoned;
  }

  public Integer getRemoveAbandonedTimeout() {
    return removeAbandonedTimeout;
  }

  public void setRemoveAbandonedTimeout(Integer removeAbandonedTimeout) {
    this.removeAbandonedTimeout = removeAbandonedTimeout;
  }

  public Boolean getLogAbandoned() {
    return logAbandoned;
  }

  public void setLogAbandoned(Boolean logAbandoned) {
    this.logAbandoned = logAbandoned;
  }
}

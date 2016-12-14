package coo.core.pns.jg;

/**
 * 极光推送配置。
 */
public class JgPnsConfig {
  /** 应用ID */
  private String appKey;
  /** 密码 */
  private String masterSecret;
  /** 是否生产环境 */
  private Boolean production = false;
  /** 消息存活时间（秒） */
  private Long liveTime = 300L;
  /** 连接超时时间（秒） */
  private Integer connTimeout = 5;
  /** 失败重发次数 */
  private Integer retryTimes = 3;
  /** 读取返回结果超时时间（秒） */
  private Integer readTimeout = 30;

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getMasterSecret() {
    return masterSecret;
  }

  public void setMasterSecret(String masterSecret) {
    this.masterSecret = masterSecret;
  }

  public Boolean getProduction() {
    return production;
  }

  public void setProduction(Boolean production) {
    this.production = production;
  }

  public Long getLiveTime() {
    return liveTime;
  }

  public void setLiveTime(Long liveTime) {
    this.liveTime = liveTime;
  }

  public Integer getConnTimeout() {
    return connTimeout;
  }

  public void setConnTimeout(Integer connTimeout) {
    this.connTimeout = connTimeout;
  }

  public Integer getRetryTimes() {
    return retryTimes;
  }

  public void setRetryTimes(Integer retryTimes) {
    this.retryTimes = retryTimes;
  }

  public Integer getReadTimeout() {
    return readTimeout;
  }

  public void setReadTimeout(Integer readTimeout) {
    this.readTimeout = readTimeout;
  }
}

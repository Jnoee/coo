package coo.core.pay.wx;

/**
 * 多商户微信支付组件工厂配置。
 */
public class WxPayFactoryConfig {
  private String key;
  private String appId;
  private String mchId;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }
}

package coo.core.pay.wft;

/**
 * 多商户威富通支付组件工厂配置。
 */
public class WftPayFactoryConfig {
  private String key;
  private String mchId;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }
}

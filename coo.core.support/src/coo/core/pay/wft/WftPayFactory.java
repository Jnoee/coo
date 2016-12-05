package coo.core.pay.wft;

import java.util.HashMap;
import java.util.Map;

/**
 * 多商户威富通支付组件工厂。
 */
public class WftPayFactory {
  private Map<String, WftPay> payMap = new HashMap<>();
  private String notifyUrl;

  /**
   * 获取微信支付组件。
   * 
   * @param config 微信支付组件工厂配置
   * @return 返回微信支付组件。
   */
  public WftPay get(WftPayFactoryConfig config) {
    WftPay wftPay = payMap.get(config.getMchId());
    if (wftPay == null) {
      wftPay = new WftPay();

      WftPayConfig wftPayConfig = new WftPayConfig();
      wftPayConfig.setKey(config.getKey());
      wftPayConfig.setMchId(config.getMchId());
      wftPayConfig.setNotifyUrl(notifyUrl);
      wftPay.setConfig(wftPayConfig);

      WftPayHttpClientFactory httpClientFactory = new WftPayHttpClientFactory();
      wftPay.setHttpClient(httpClientFactory.createInstance());

      WftPayRequestConfigFactory requestConfigFactory = new WftPayRequestConfigFactory();
      wftPay.setRequestConfig(requestConfigFactory.createInstance());

      payMap.put(config.getMchId(), wftPay);
    }
    return wftPay;
  }

  public Map<String, WftPay> getPayMap() {
    return payMap;
  }

  public void setPayMap(Map<String, WftPay> payMap) {
    this.payMap = payMap;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
}

package coo.core.pay.wx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多商户微信支付组件工厂。
 */
public class WxPayFactory {
  private Map<String, WxPay> payMap = new HashMap<>();
  private String certDir;
  private String notifyUrl;

  /**
   * 获取微信支付组件。
   * 
   * @param config 微信支付组件工厂配置
   * @return 返回微信支付组件。
   */
  public WxPay get(WxPayFactoryConfig config) {
    WxPay wxPay = payMap.get(config.getMchId());
    if (wxPay == null) {
      wxPay = new WxPay();

      WxPayConfig wxPayConfig = new WxPayConfig();
      wxPayConfig.setAppId(config.getAppId());
      wxPayConfig.setKey(config.getKey());
      wxPayConfig.setMchId(config.getMchId());
      wxPayConfig.setNotifyUrl(notifyUrl);
      wxPay.setConfig(wxPayConfig);

      WxPayHttpClientFactory httpClientFactory = new WxPayHttpClientFactory();
      httpClientFactory.setCertFilePath(certDir + "/" + config.getMchId() + ".p12");
      httpClientFactory.setCertPassword(config.getMchId());
      wxPay.setHttpClient(httpClientFactory.createInstance());

      WxPayRequestConfigFactory requestConfigFactory = new WxPayRequestConfigFactory();
      wxPay.setRequestConfig(requestConfigFactory.createInstance());

      payMap.put(config.getMchId(), wxPay);
    }
    return wxPay;
  }

  /**
   * 清除支付组件缓存。
   */
  public void clear() {
    payMap.clear();
  }

  /**
   * 获取商户号列表。
   * 
   * @return 返回商户号列表。
   */
  public List<String> getMchIds() {
    return new ArrayList<>(payMap.keySet());
  }

  public String getCertDir() {
    return certDir;
  }

  public void setCertDir(String certDir) {
    this.certDir = certDir;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
}

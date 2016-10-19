package coo.core.pay.ali;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付组件工厂。
 */
public class AliPayFactory {
  private Map<String, AliPay> payMap = new HashMap<>();
  private String certDir;
  private String notifyUrl;

  /**
   * 获取支付宝支付组件。
   * 
   * @param config 支付宝支付组件工厂配置
   * @return 返回支付宝支付组件。
   */
  public AliPay get(AliPayFactoryConfig config) {
    AliPay aliPay = payMap.get(config.getPartner());
    if (aliPay == null) {
      aliPay = new AliPay();

      AliPayConfig aliPayConfig = new AliPayConfig();
      aliPayConfig.setPartner(config.getPartner());
      aliPayConfig.setNotifyUrl(notifyUrl);
      aliPayConfig.setPublicKeyFilePath(certDir + "/ali_public_key.pem");
      aliPayConfig.setPrivateKeyFilePath(certDir + "/" + config.getPartner() + ".pem");
      aliPayConfig.init();
      aliPay.setConfig(aliPayConfig);

      AliPayHttpClientFactory httpClientFactory = new AliPayHttpClientFactory();
      aliPay.setHttpClient(httpClientFactory.createInstance());

      AliPayRequestConfigFactory requestConfigFactory = new AliPayRequestConfigFactory();
      aliPay.setRequestConfig(requestConfigFactory.createInstance());
    }
    return aliPay;
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

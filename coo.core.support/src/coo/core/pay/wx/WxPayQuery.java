package coo.core.pay.wx;

/**
 * 微信支付请求基类。
 */
public abstract class WxPayQuery extends WxPayData {
  /**
   * 获取接口名称。
   * 
   * @return 返回接口名称。
   */
  public abstract String getApiName();

  /**
   * 生成请求XML。
   * 
   * @param key 密钥
   * @return 返回请求XML。
   */
  public String genXml(String key) {
    sign = WxPayUtils.sign(this, key);
    return getXstream().toXML(this);
  }
}

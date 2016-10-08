package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信支付请求基类。
 */
public abstract class WxPayQuery {
  @XStreamAlias("appid")
  protected String appId;
  @XStreamAlias("mch_id")
  protected String mchId;
  @XStreamAlias("nonce_str")
  protected String nonceStr;
  @XStreamAlias("sign")
  protected String sign;

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
    return WxPayUtils.getXstream(getClass()).toXML(this);
  }

  protected String getAppId() {
    return appId;
  }

  protected void setAppId(String appId) {
    this.appId = appId;
  }

  protected String getMchId() {
    return mchId;
  }

  protected void setMchId(String mchId) {
    this.mchId = mchId;
  }

  protected String getNonceStr() {
    return nonceStr;
  }

  protected void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  protected String getSign() {
    return sign;
  }

  protected void setSign(String sign) {
    this.sign = sign;
  }
}

package coo.core.pay.wx;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.exception.UncheckedException;

/**
 * 微信支付请求基类。
 */
public abstract class WxPayQuery {
  @JacksonXmlProperty(localName = "appid")
  protected String appId;
  @JacksonXmlProperty(localName = "mch_id")
  protected String mchId;
  @JacksonXmlProperty(localName = "nonce_str")
  protected String nonceStr;
  @JacksonXmlProperty(localName = "sign")
  protected String sign;

  /**
   * 获取接口地址。
   * 
   * @return 返回接口地址。
   */
  public abstract String getService();

  /**
   * 生成请求XML。
   * 
   * @param key 密钥
   * @return 返回请求XML。
   */
  public String genXml(String key) {
    sign = WxPayUtils.sign(this, key);
    try {
      XmlMapper xmlMapper = new XmlMapper();
      return xmlMapper.writeValueAsString(this);
    } catch (Exception e) {
      throw new UncheckedException("生成XML时发生异常。", e);
    }
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

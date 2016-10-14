package coo.core.pay.wft;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 威富通支付请求基类。
 */
public abstract class WftPayQuery {
  @XStreamAlias("service")
  protected String service;
  @XStreamAlias("version")
  protected String version;
  @XStreamAlias("charset")
  protected String charset;
  @XStreamAlias("mch_id")
  protected String mchId;
  @XStreamAlias("nonce_str")
  protected String nonceStr;
  @XStreamAlias("sign_type")
  protected String signType;
  @XStreamAlias("sign")
  protected String sign;

  /**
   * 生成请求XML。
   * 
   * @param key 密钥
   * @return 返回请求XML。
   */
  public String genXml(String key) {
    sign = WftPayUtils.sign(this, key);
    return WftPayUtils.getXstream(getClass()).toXML(this);
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}

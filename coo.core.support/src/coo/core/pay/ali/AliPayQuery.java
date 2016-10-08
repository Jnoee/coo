package coo.core.pay.ali;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 支付宝支付请求基类。
 */
public abstract class AliPayQuery {
  @XStreamAlias("service")
  protected String service;
  @XStreamAlias("partner")
  protected String partner;
  @XStreamAlias("_input_charset")
  protected String inputCharset = "UTF-8";
  @XStreamAlias("sign_type")
  protected String signType = "RSA";
  @XStreamAlias("sign")
  protected String sign;

  /**
   * 生成请求参数字符串。
   * 
   * @return 返回请求参数字符串。
   */
  public String genParams() {
    return AliPayUtils.genParams(this);
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }

  public String getInputCharset() {
    return inputCharset;
  }

  public void setInputCharset(String inputCharset) {
    this.inputCharset = inputCharset;
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

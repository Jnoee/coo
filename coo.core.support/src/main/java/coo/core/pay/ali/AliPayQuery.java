package coo.core.pay.ali;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 支付宝支付请求基类。
 */
@JacksonXmlRootElement(localName = "alipay")
public abstract class AliPayQuery {
  @JacksonXmlProperty(localName = "service")
  protected String service;
  @JacksonXmlProperty(localName = "partner")
  protected String partner;
  @JacksonXmlProperty(localName = "_input_charset")
  protected String inputCharset = "UTF-8";
  @JacksonXmlProperty(localName = "sign_type")
  protected String signType = "RSA";
  @JacksonXmlProperty(localName = "sign")
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

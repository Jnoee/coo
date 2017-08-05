package coo.core.pay.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 调起支付请求。（APP端使用）
 */
public class PayQuery {
  @JacksonXmlProperty(localName = "appid")
  private String appId;
  @JacksonXmlProperty(localName = "partnerid")
  private String partnerId;
  @JacksonXmlProperty(localName = "prepayid")
  private String prepayId;
  @JacksonXmlProperty(localName = "package")
  private String extendField;
  @JacksonXmlProperty(localName = "timestamp")
  private String timestamp;
  @JacksonXmlProperty(localName = "noncestr")
  private String nonceStr;
  @JacksonXmlProperty(localName = "sign")
  private String sign;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(String partnerId) {
    this.partnerId = partnerId;
  }

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }

  public String getExtendField() {
    return extendField;
  }

  public void setExtendField(String extendField) {
    this.extendField = extendField;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}

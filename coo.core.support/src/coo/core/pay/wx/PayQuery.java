package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 调起支付请求。（APP端使用）
 */
public class PayQuery {
  @XStreamAlias("appid")
  private String appId;
  @XStreamAlias("partnerid")
  private String partnerId;
  @XStreamAlias("prepayid")
  private String prepayId;
  @XStreamAlias("package")
  private String extendField;
  @XStreamAlias("timestamp")
  private String timestamp;
  @XStreamAlias("noncestr")
  private String nonceStr;
  @XStreamAlias("sign")
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

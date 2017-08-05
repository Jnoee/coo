package coo.core.pay.ali;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.util.BeanUtils;

/**
 * 申请支付请求。
 */
public class PayQuery extends AliPayQuery {
  @JacksonXmlProperty(localName = "notify_url")
  private String notifyUrl;
  @JacksonXmlProperty(localName = "out_trade_no")
  private String outTradeNo;
  @JacksonXmlProperty(localName = "subject")
  private String subject;
  @JacksonXmlProperty(localName = "payment_type")
  private String paymentType = "1";
  @JacksonXmlProperty(localName = "seller_id")
  private String sellerId;
  @JacksonXmlProperty(localName = "total_fee")
  private Double totalFee;
  @JacksonXmlProperty(localName = "body")
  private String body;

  /**
   * 构造方法。
   * 
   * @param data 业务数据
   */
  public PayQuery(PayData data) {
    service = "mobile.securitypay.pay";
    BeanUtils.copyFields(data, this);
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public Double getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Double totalFee) {
    this.totalFee = totalFee;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}

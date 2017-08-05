package coo.core.pay.wx;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.util.BeanUtils;

/**
 * 微信支付通知。
 */
public class PayNotify extends WxPayReply {
  @JacksonXmlProperty(localName = "openid")
  private String openId;
  @JacksonXmlProperty(localName = "is_subscribe")
  private String isSubscribe;
  @JacksonXmlProperty(localName = "trade_type")
  private String tradeType;
  @JacksonXmlProperty(localName = "bank_type")
  private String bankType;
  @JacksonXmlProperty(localName = "total_fee")
  private Integer totalFee;
  @JacksonXmlProperty(localName = "fee_type")
  private String feeType;
  @JacksonXmlProperty(localName = "cash_fee")
  private Integer cashFee;
  @JacksonXmlProperty(localName = "cash_fee_type")
  private String cashFeeType;
  @JacksonXmlProperty(localName = "coupon_fee")
  private Integer couponFee;
  @JacksonXmlProperty(localName = "coupon_count")
  private Integer couponCount;
  @JacksonXmlProperty(localName = "transaction_id")
  private String transactionId;
  @JacksonXmlProperty(localName = "out_trade_no")
  private String outTradeNo;
  @JacksonXmlProperty(localName = "attach")
  private String attach;
  @JacksonXmlProperty(localName = "time_end")
  private String timeEnd;

  /**
   * 构造方法。
   * 
   * @param request 微信支付通知的HTTP请求
   * @param key 密钥
   */
  public PayNotify(HttpServletRequest request, String key) {
    String xml = WxPayUtils.genXml(request);
    WxPayUtils.parseXml(xml, key, getClass());
  }

  /**
   * 构造方法。
   * 
   * @param xml 微信支付通知XML内容
   * @param key 密钥
   */
  public PayNotify(String xml, String key) {
    WxPayUtils.parseXml(xml, key, getClass());
  }

  /**
   * 获取业务数据。
   * 
   * @return 返回业务数据。
   */
  public PayNotifyData getData() {
    PayNotifyData data = new PayNotifyData();
    BeanUtils.copyFields(this, data);
    return data;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getIsSubscribe() {
    return isSubscribe;
  }

  public void setIsSubscribe(String isSubscribe) {
    this.isSubscribe = isSubscribe;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getBankType() {
    return bankType;
  }

  public void setBankType(String bankType) {
    this.bankType = bankType;
  }

  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public Integer getCashFee() {
    return cashFee;
  }

  public void setCashFee(Integer cashFee) {
    this.cashFee = cashFee;
  }

  public String getCashFeeType() {
    return cashFeeType;
  }

  public void setCashFeeType(String cashFeeType) {
    this.cashFeeType = cashFeeType;
  }

  public Integer getCouponFee() {
    return couponFee;
  }

  public void setCouponFee(Integer couponFee) {
    this.couponFee = couponFee;
  }

  public Integer getCouponCount() {
    return couponCount;
  }

  public void setCouponCount(Integer couponCount) {
    this.couponCount = couponCount;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }
}

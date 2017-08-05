package coo.core.pay.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 申请退款响应。
 */
public class RefundReply extends WxPayReply {
  @JacksonXmlProperty(localName = "transaction_id")
  private String transactionId;
  @JacksonXmlProperty(localName = "out_trade_no")
  private String outTradeNo;
  @JacksonXmlProperty(localName = "out_refund_no")
  private String outRefundNo;
  @JacksonXmlProperty(localName = "refund_id")
  private String refundId;
  @JacksonXmlProperty(localName = "refund_channel")
  private String refundChannel;
  @JacksonXmlProperty(localName = "refund_fee")
  private Integer refundFee;
  @JacksonXmlProperty(localName = "total_fee")
  private Integer totalFee;
  @JacksonXmlProperty(localName = "fee_type")
  private String feeType;
  @JacksonXmlProperty(localName = "cash_fee")
  private Integer cashFee;
  @JacksonXmlProperty(localName = "cash_refund_fee")
  private Integer cashRefundFee;
  @JacksonXmlProperty(localName = "coupon_refund_fee")
  private Integer couponRefundFee;
  @JacksonXmlProperty(localName = "coupon_refund_count")
  private Integer couponRefundCount;
  @JacksonXmlProperty(localName = "coupon_refund_id")
  private String couponRefundId;

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

  public String getOutRefundNo() {
    return outRefundNo;
  }

  public void setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
  }

  public String getRefundId() {
    return refundId;
  }

  public void setRefundId(String refundId) {
    this.refundId = refundId;
  }

  public String getRefundChannel() {
    return refundChannel;
  }

  public void setRefundChannel(String refundChannel) {
    this.refundChannel = refundChannel;
  }

  public Integer getRefundFee() {
    return refundFee;
  }

  public void setRefundFee(Integer refundFee) {
    this.refundFee = refundFee;
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

  public Integer getCashRefundFee() {
    return cashRefundFee;
  }

  public void setCashRefundFee(Integer cashRefundFee) {
    this.cashRefundFee = cashRefundFee;
  }

  public Integer getCouponRefundFee() {
    return couponRefundFee;
  }

  public void setCouponRefundFee(Integer couponRefundFee) {
    this.couponRefundFee = couponRefundFee;
  }

  public Integer getCouponRefundCount() {
    return couponRefundCount;
  }

  public void setCouponRefundCount(Integer couponRefundCount) {
    this.couponRefundCount = couponRefundCount;
  }

  public String getCouponRefundId() {
    return couponRefundId;
  }

  public void setCouponRefundId(String couponRefundId) {
    this.couponRefundId = couponRefundId;
  }
}

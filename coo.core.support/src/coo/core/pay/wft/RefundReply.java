package coo.core.pay.wft;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 申请退款响应。
 */
public class RefundReply extends WftPayReply {
  @XStreamAlias("transaction_id")
  private String transactionId;
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("out_refund_no")
  private String outRefundNo;
  @XStreamAlias("refund_id")
  private String refundId;
  @XStreamAlias("refund_channel")
  private String refundChannel;
  @XStreamAlias("refund_fee")
  private Integer refundFee;
  @XStreamAlias("coupon_refund_fee")
  private Integer couponRefundFee;

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

  public Integer getCouponRefundFee() {
    return couponRefundFee;
  }

  public void setCouponRefundFee(Integer couponRefundFee) {
    this.couponRefundFee = couponRefundFee;
  }
}

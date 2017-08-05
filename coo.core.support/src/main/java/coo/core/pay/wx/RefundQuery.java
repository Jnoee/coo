package coo.core.pay.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.util.BeanUtils;

/**
 * 申请退款请求。
 */
public class RefundQuery extends WxPayQuery {
  @JacksonXmlProperty(localName = "out_trade_no")
  private String outTradeNo;
  @JacksonXmlProperty(localName = "out_refund_no")
  private String outRefundNo;
  @JacksonXmlProperty(localName = "total_fee")
  private Integer totalFee;
  @JacksonXmlProperty(localName = "refund_fee")
  private Integer refundFee;
  @JacksonXmlProperty(localName = "refund_fee_type")
  private String refundFeeType;
  @JacksonXmlProperty(localName = "op_user_id")
  private String opUserId;
  @JacksonXmlProperty(localName = "refund_account")
  private String refundAccount;

  /**
   * 构造方法。
   * 
   * @param data 业务数据
   */
  public RefundQuery(RefundData data) {
    BeanUtils.copyFields(data, this);
  }

  @Override
  public String getService() {
    return "/secapi/pay/refund";
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

  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public Integer getRefundFee() {
    return refundFee;
  }

  public void setRefundFee(Integer refundFee) {
    this.refundFee = refundFee;
  }

  public String getRefundFeeType() {
    return refundFeeType;
  }

  public void setRefundFeeType(String refundFeeType) {
    this.refundFeeType = refundFeeType;
  }

  public String getOpUserId() {
    return opUserId;
  }

  public void setOpUserId(String opUserId) {
    this.opUserId = opUserId;
  }

  public String getRefundAccount() {
    return refundAccount;
  }

  public void setRefundAccount(String refundAccount) {
    this.refundAccount = refundAccount;
  }
}

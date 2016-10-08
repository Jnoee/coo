package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.util.BeanUtils;

/**
 * 申请退款请求。
 */
public class RefundQuery extends WxPayQuery {
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("out_refund_no")
  private String outRefundNo;
  @XStreamAlias("total_fee")
  private Integer totalFee;
  @XStreamAlias("refund_fee")
  private Integer refundFee;
  @XStreamAlias("refund_fee_type")
  private String refundFeeType;
  @XStreamAlias("op_user_id")
  private String opUserId;
  @XStreamAlias("refund_account")
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

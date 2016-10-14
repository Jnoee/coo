package coo.core.pay.wft;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.util.BeanUtils;

/**
 * 申请退款请求。
 */
public class RefundQuery extends WftPayQuery {
  @XStreamAlias("sign_agentno")
  private String signAgentno;
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("out_refund_no")
  private String outRefundNo;
  @XStreamAlias("total_fee")
  private Integer totalFee;
  @XStreamAlias("refund_fee")
  private Integer refundFee;
  @XStreamAlias("op_user_id")
  private String opUserId;
  @XStreamAlias("refund_channel")
  private String refundChannel;

  /**
   * 构造方法。
   * 
   * @param data 业务数据
   */
  public RefundQuery(RefundData data) {
    service = "unified.trade.refund";
    BeanUtils.copyFields(data, this);
  }

  public String getSignAgentno() {
    return signAgentno;
  }

  public void setSignAgentno(String signAgentno) {
    this.signAgentno = signAgentno;
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

  public String getOpUserId() {
    return opUserId;
  }

  public void setOpUserId(String opUserId) {
    this.opUserId = opUserId;
  }

  public String getRefundChannel() {
    return refundChannel;
  }

  public void setRefundChannel(String refundChannel) {
    this.refundChannel = refundChannel;
  }
}

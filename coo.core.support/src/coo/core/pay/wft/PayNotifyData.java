package coo.core.pay.wft;

/**
 * 威富通支付通知业务数据。
 */
public class PayNotifyData {
  private String openId;
  private String transactionId;
  private String outTransactionId;
  private String outTradeNo;
  private Integer totalFee;
  private String attach;

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getOutTransactionId() {
    return outTransactionId;
  }

  public void setOutTransactionId(String outTransactionId) {
    this.outTransactionId = outTransactionId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }
}

package coo.core.pay.wft;

import java.util.Date;

/**
 * 预下单业务数据。
 */
public class PayData {
  private String outTradeNo;
  private String body;
  private String attach;
  private Integer totalFee;
  private String mchCreateIp;
  private Date timeStart;
  private Date timeExpire;
  private Boolean limitCreditPay;
  private String goodsTag;

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public String getMchCreateIp() {
    return mchCreateIp;
  }

  public void setMchCreateIp(String mchCreateIp) {
    this.mchCreateIp = mchCreateIp;
  }

  public Date getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(Date timeStart) {
    this.timeStart = timeStart;
  }

  public Date getTimeExpire() {
    return timeExpire;
  }

  public void setTimeExpire(Date timeExpire) {
    this.timeExpire = timeExpire;
  }

  public Boolean getLimitCreditPay() {
    return limitCreditPay;
  }

  public void setLimitCreditPay(Boolean limitCreditPay) {
    this.limitCreditPay = limitCreditPay;
  }

  public String getGoodsTag() {
    return goodsTag;
  }

  public void setGoodsTag(String goodsTag) {
    this.goodsTag = goodsTag;
  }
}

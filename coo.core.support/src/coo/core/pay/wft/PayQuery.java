package coo.core.pay.wft;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.util.BeanUtils;
import coo.base.util.DateUtils;

/**
 * 预下单请求。
 */
public class PayQuery extends WftPayQuery {
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("device_info")
  private String deviceInfo;
  @XStreamAlias("body")
  private String body;
  @XStreamAlias("attach")
  private String attach;
  @XStreamAlias("total_fee")
  private Integer totalFee;
  @XStreamAlias("mch_create_ip")
  private String mchCreateIp;
  @XStreamAlias("notify_url")
  private String notifyUrl;
  @XStreamAlias("time_start")
  private String timeStart;
  @XStreamAlias("time_expire")
  private String timeExpire;
  @XStreamAlias("limit_credit_pay")
  private String limitCreditPay;
  @XStreamAlias("goods_tag")
  private String goodsTag;

  /**
   * 构造方法。
   * 
   * @param data 业务数据
   */
  public PayQuery(PayData data) {
    service = "unified.trade.pay";
    BeanUtils.copyFields(data, this);
    if (data.getTimeStart() != null) {
      timeStart = DateUtils.format(data.getTimeStart(), DateUtils.SECOND_N);
    }
    if (data.getTimeExpire() != null) {
      timeExpire = DateUtils.format(data.getTimeExpire(), DateUtils.SECOND_N);
    }
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
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

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }

  public String getTimeExpire() {
    return timeExpire;
  }

  public void setTimeExpire(String timeExpire) {
    this.timeExpire = timeExpire;
  }

  public String getLimitCreditPay() {
    return limitCreditPay;
  }

  public void setLimitCreditPay(String limitCreditPay) {
    this.limitCreditPay = limitCreditPay;
  }

  public String getGoodsTag() {
    return goodsTag;
  }

  public void setGoodsTag(String goodsTag) {
    this.goodsTag = goodsTag;
  }
}

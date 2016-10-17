package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.util.BeanUtils;
import coo.base.util.DateUtils;

/**
 * 统一下单请求。
 */
public class UnifiedOrderQuery extends WxPayQuery {
  @XStreamAlias("device_info")
  private String deviceInfo;
  @XStreamAlias("body")
  private String body;
  @XStreamAlias("detail")
  private String detail;
  @XStreamAlias("attach")
  private String attach;
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("fee_type")
  private String feeType;
  @XStreamAlias("total_fee")
  private Integer totalFee;
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;
  @XStreamAlias("time_start")
  private String timeStart;
  @XStreamAlias("time_expire")
  private String timeExpire;
  @XStreamAlias("goods_tag")
  private String goodsTag;
  @XStreamAlias("notify_url")
  private String notifyUrl;
  @XStreamAlias("trade_type")
  private String tradeType = "APP";
  @XStreamAlias("limit_pay")
  private String limitPay;

  /**
   * 构造方法。
   * 
   * @param data 业务数据
   */
  public UnifiedOrderQuery(PayData data) {
    BeanUtils.copyFields(data, this);
    if (data.getTimeStart() != null) {
      timeStart = DateUtils.format(data.getTimeStart(), DateUtils.SECOND_N);
    }
    if (data.getTimeExpire() != null) {
      timeExpire = DateUtils.format(data.getTimeExpire(), DateUtils.SECOND_N);
    }
  }

  @Override
  public String getService() {
    return "/pay/unifiedorder";
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

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public String getSpbillCreateIp() {
    return spbillCreateIp;
  }

  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
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

  public String getGoodsTag() {
    return goodsTag;
  }

  public void setGoodsTag(String goodsTag) {
    this.goodsTag = goodsTag;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getLimitPay() {
    return limitPay;
  }

  public void setLimitPay(String limitPay) {
    this.limitPay = limitPay;
  }
}

package coo.core.pay.wx;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单请求。
 */
public class UnifiedOrderQuery extends WxPayQuery {
  /** 设备号 */
  @XStreamAlias("device_info")
  private String deviceInfo;
  /** 商品描述 */
  @XStreamAlias("body")
  @NotBlank
  @Length(max = 128)
  private String body;
  /** 商品详情 */
  @XStreamAlias("detail")
  @Length(max = 8192)
  private String detail;
  /** 附加数据 */
  @XStreamAlias("attach")
  @Length(max = 127)
  private String attach;
  /** 商户订单号 */
  @XStreamAlias("out_trade_no")
  @NotBlank
  @Length(max = 32)
  private String outTradeNo;
  /** 货币类型 */
  @XStreamAlias("fee_type")
  @Length(max = 16)
  private String feeType;
  /** 总金额 */
  @XStreamAlias("total_fee")
  @NotNull
  private Integer totalFee;
  /** 终端IP */
  @XStreamAlias("spbill_create_ip")
  @NotBlank
  @Length(max = 16)
  private String spbillCreateIp;
  /** 交易起始时间 */
  @XStreamAlias("time_start")
  @Length(max = 14)
  private String timeStart;
  /** 交易结束时间 */
  @XStreamAlias("time_expire")
  @Length(max = 14)
  private String timeExpire;
  /** 商品标记 */
  @XStreamAlias("goods_tag")
  @Length(max = 32)
  private String goodsTag;
  /** 通知地址 */
  @XStreamAlias("notify_url")
  @NotBlank
  @Length(max = 256)
  private String notifyUrl;
  /** 交易类型 */
  @XStreamAlias("trade_type")
  @NotBlank
  @Length(max = 16)
  private String tradeType;
  /** 限制支付方式 */
  @XStreamAlias("limit_pay")
  @Length(max = 32)
  private String limitPay;

  @Override
  public String getApiName() {
    return "/unifiedorder";
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

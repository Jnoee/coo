package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单响应。
 */
public class UnifiedOrderReply extends WxPayReply {
  /** 设备号 */
  @XStreamAlias("device_info")
  private String deviceInfo;
  /** 交易类型 */
  @XStreamAlias("trade_type")
  private String tradeType;
  /** 预支付交易会话标识 */
  @XStreamAlias("prepay_id")
  private String prepayId;

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }
}

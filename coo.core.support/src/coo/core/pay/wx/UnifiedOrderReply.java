package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单响应。
 */
public class UnifiedOrderReply extends WxPayReply {
  @XStreamAlias("device_info")
  private String deviceInfo;
  @XStreamAlias("trade_type")
  private String tradeType;
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

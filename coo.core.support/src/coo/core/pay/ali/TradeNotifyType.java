package coo.core.pay.ali;

/**
 * 支付宝交易通知类型。
 */
public enum TradeNotifyType {
  WAIT_BUYER_PAY("等待支付"), TRADE_SUCCESS("支付成功"), TRADE_FINISHED("交易成功"), TRADE_CLOSED(
      "交易关闭"), FULL_REFUND_SUCCESS("全额退款成功"), PART_REFUND_SUCCESS("部分退款成功"), REFUND_CLOSED("退款关闭");

  private String desc;

  /**
   * 构造方法。
   * 
   * @param desc 类型说明
   */
  private TradeNotifyType(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}

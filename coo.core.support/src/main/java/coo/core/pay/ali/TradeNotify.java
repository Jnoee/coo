package coo.core.pay.ali;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.exception.BusinessException;
import coo.base.util.BeanUtils;

/**
 * 支付宝交易通知。
 */
public class TradeNotify {
  @JacksonXmlProperty(localName = "notify_time")
  private String notifyTime;
  @JacksonXmlProperty(localName = "notify_type")
  private String notifyType;
  @JacksonXmlProperty(localName = "notify_id")
  private String notifyId;
  @JacksonXmlProperty(localName = "sign_type")
  private String signType;
  @JacksonXmlProperty(localName = "sign")
  private String sign;
  @JacksonXmlProperty(localName = "out_trade_no")
  private String outTradeNo;
  @JacksonXmlProperty(localName = "subject")
  private String subject;
  @JacksonXmlProperty(localName = "payment_type")
  private String paymentType;
  @JacksonXmlProperty(localName = "trade_no")
  private String tradeNo;
  @JacksonXmlProperty(localName = "trade_status")
  private String tradeStatus;
  @JacksonXmlProperty(localName = "seller_id")
  private String sellerId;
  @JacksonXmlProperty(localName = "seller_email")
  private String sellerEmail;
  @JacksonXmlProperty(localName = "buyer_id")
  private String buyerId;
  @JacksonXmlProperty(localName = "buyer_email")
  private String buyerEmail;
  @JacksonXmlProperty(localName = "total_fee")
  private String totalFee;
  @JacksonXmlProperty(localName = "quantity")
  private String quantity;
  @JacksonXmlProperty(localName = "price")
  private String price;
  @JacksonXmlProperty(localName = "body")
  private String body;
  @JacksonXmlProperty(localName = "gmt_create")
  private String gmtCreate;
  @JacksonXmlProperty(localName = "gmt_payment")
  private String gmtPayment;
  @JacksonXmlProperty(localName = "is_total_fee_adjust")
  private String isTotalFeeAdjust;
  @JacksonXmlProperty(localName = "use_coupon")
  private String useCoupon;
  @JacksonXmlProperty(localName = "discount")
  private String discount;
  @JacksonXmlProperty(localName = "refund_status")
  private String refundStatus;
  @JacksonXmlProperty(localName = "gmt_refund")
  private String gmtRefund;
  @JacksonXmlProperty(localName = "gmt_close")
  private String gmtClose;

  /**
   * 构造方法。
   * 
   * @param request 支付宝交易通知HTTP请求
   * @param publicKey 支付宝公钥
   */
  public TradeNotify(HttpServletRequest request, String publicKey) {
    Map<String, String> paramsMap = AliPayUtils.genMap(request);
    AliPayUtils.fillData(paramsMap, this);
    if (!AliPayUtils.verify(this, publicKey)) {
      throw new BusinessException("支付宝支付结果通知失败：验证签名失败，返回数据为[" + paramsMap + "]");
    }
  }

  /**
   * 获取业务数据。
   * 
   * @return 返回业务数据。
   */
  public TradeNotifyData getData() {
    TradeNotifyData data = new TradeNotifyData();
    BeanUtils.copyFields(this, data);
    if ("TRADE_CLOSED".equals(tradeStatus) && "REFUND_SUCCESS".equals(refundStatus)) {
      data.setNotifyType(TradeNotifyType.FULL_REFUND_SUCCESS);
    } else if ("TRADE_SUCCESS".equals(tradeStatus) && "REFUND_SUCCESS".equals(refundStatus)) {
      data.setNotifyType(TradeNotifyType.PART_REFUND_SUCCESS);
    } else if ("REFUND_CLOSED".equals(refundStatus)) {
      data.setNotifyType(TradeNotifyType.REFUND_CLOSED);
    } else {
      data.setNotifyType(TradeNotifyType.valueOf(tradeStatus));
    }
    data.setTotalFee(Double.valueOf(totalFee));
    data.setQuantity(Integer.valueOf(quantity));
    data.setPrice(Double.valueOf(price));
    return data;
  }

  public String getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(String notifyTime) {
    this.notifyTime = notifyTime;
  }

  public String getNotifyType() {
    return notifyType;
  }

  public void setNotifyType(String notifyType) {
    this.notifyType = notifyType;
  }

  public String getNotifyId() {
    return notifyId;
  }

  public void setNotifyId(String notifyId) {
    this.notifyId = notifyId;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getSellerEmail() {
    return sellerEmail;
  }

  public void setSellerEmail(String sellerEmail) {
    this.sellerEmail = sellerEmail;
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId;
  }

  public String getBuyerEmail() {
    return buyerEmail;
  }

  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail;
  }

  public String getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(String gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public String getGmtPayment() {
    return gmtPayment;
  }

  public void setGmtPayment(String gmtPayment) {
    this.gmtPayment = gmtPayment;
  }

  public String getIsTotalFeeAdjust() {
    return isTotalFeeAdjust;
  }

  public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
    this.isTotalFeeAdjust = isTotalFeeAdjust;
  }

  public String getUseCoupon() {
    return useCoupon;
  }

  public void setUseCoupon(String useCoupon) {
    this.useCoupon = useCoupon;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public String getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(String refundStatus) {
    this.refundStatus = refundStatus;
  }

  public String getGmtRefund() {
    return gmtRefund;
  }

  public void setGmtRefund(String gmtRefund) {
    this.gmtRefund = gmtRefund;
  }

  public String getGmtClose() {
    return gmtClose;
  }

  public void setGmtClose(String gmtClose) {
    this.gmtClose = gmtClose;
  }
}

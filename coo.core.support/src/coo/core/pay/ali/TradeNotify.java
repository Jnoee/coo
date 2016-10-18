package coo.core.pay.ali;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.exception.BusinessException;
import coo.base.util.BeanUtils;
import coo.base.util.StringUtils;

/**
 * 支付宝交易通知。
 */
public class TradeNotify {
  @XStreamAlias("notify_time")
  private String notifyTime;
  @XStreamAlias("notify_type")
  private String notifyType;
  @XStreamAlias("notify_id")
  private String notifyId;
  @XStreamAlias("sign_type")
  private String signType;
  @XStreamAlias("sign")
  private String sign;
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("subject")
  private String subject;
  @XStreamAlias("payment_type")
  private String paymentType;
  @XStreamAlias("trade_no")
  private String tradeNo;
  @XStreamAlias("trade_status")
  private String tradeStatus;
  @XStreamAlias("seller_id")
  private String sellerId;
  @XStreamAlias("seller_email")
  private String sellerEmail;
  @XStreamAlias("buyer_id")
  private String buyerId;
  @XStreamAlias("buyer_email")
  private String buyerEmail;
  @XStreamAlias("total_fee")
  private String totalFee;
  @XStreamAlias("quantity")
  private String quantity;
  @XStreamAlias("price")
  private String price;
  @XStreamAlias("body")
  private String body;
  @XStreamAlias("gmt_create")
  private String gmtCreate;
  @XStreamAlias("gmt_payment")
  private String gmtPayment;
  @XStreamAlias("is_total_fee_adjust")
  private String isTotalFeeAdjust;
  @XStreamAlias("use_coupon")
  private String useCoupon;
  @XStreamAlias("discount")
  private String discount;
  @XStreamAlias("refund_status")
  private String refundStatus;
  @XStreamAlias("gmt_refund")
  private String gmtRefund;
  @XStreamAlias("gmt_close")
  private String gmtClose;

  /**
   * 构造方法。
   * 
   * @param request 支付宝交易通知HTTP请求
   * @param publicKey 支付宝公钥
   */
  public TradeNotify(HttpServletRequest request, String publicKey) {
    Map<String, String> paramsMap = genMap(request);
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

  /**
   * 将HTTP请求参数转换成Map。
   * 
   * @param request HTTP请求
   * @return 返回参数Map。
   */
  private static Map<String, String> genMap(HttpServletRequest request) {
    Map<String, String[]> paramsMap = request.getParameterMap();
    Map<String, String> resultMap = new HashMap<String, String>();
    for (Entry<String, String[]> param : paramsMap.entrySet()) {
      resultMap.put(param.getKey(), StringUtils.join(param.getValue(), ","));
    }
    return resultMap;
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

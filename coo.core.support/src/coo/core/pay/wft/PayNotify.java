package coo.core.pay.wft;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.constants.Encoding;
import coo.base.exception.BusinessException;
import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.FileUtils;

/**
 * 威富通支付通知。
 */
public class PayNotify extends WftPayReply {
  @XStreamAlias("openid")
  private String openId;
  @XStreamAlias("trade_type")
  private String tradeType;
  @XStreamAlias("pay_result")
  private Integer payResult;
  @XStreamAlias("pay_info")
  private String payInfo;
  @XStreamAlias("transaction_id")
  private String transactionId;
  @XStreamAlias("out_transaction_id")
  private String outTransactionId;
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  @XStreamAlias("total_fee")
  private Integer totalFee;
  @XStreamAlias("fee_type")
  private String feeType;
  @XStreamAlias("attach")
  private String attach;
  @XStreamAlias("bank_type")
  private String bankType;
  @XStreamAlias("time_end")
  private String timeEnd;



  /**
   * 构造方法。
   * 
   * @param request 微信支付通知的HTTP请求
   * @param key 密钥
   */
  public PayNotify(HttpServletRequest request, String key) {
    try (InputStream xmlIn = request.getInputStream()) {
      byte[] xmlBytes = FileUtils.toByteArray(xmlIn);
      String xml = new String(xmlBytes, Encoding.UTF_8);
      parseXml(xml, key);
    } catch (UnsupportedEncodingException e) {
      throw new UncheckedException(e);
    } catch (IOException e) {
      throw new UncheckedException(e);
    }
  }

  /**
   * 获取业务数据。
   * 
   * @return 返回业务数据。
   */
  public PayNotifyData getData() {
    PayNotifyData data = new PayNotifyData();
    BeanUtils.copyFields(this, data);
    return data;
  }

  @Override
  public void parseXml(String xml, String key) {
    super.parseXml(xml, key);
    if (payResult != null && 0 != payResult) {
      throw new BusinessException("威富通支付交易失败：" + payInfo);
    }
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public Integer getPayResult() {
    return payResult;
  }

  public void setPayResult(Integer payResult) {
    this.payResult = payResult;
  }

  public String getPayInfo() {
    return payInfo;
  }

  public void setPayInfo(String payInfo) {
    this.payInfo = payInfo;
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

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getBankType() {
    return bankType;
  }

  public void setBankType(String bankType) {
    this.bankType = bankType;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }
}

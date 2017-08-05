package coo.core.pay.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信支付响应基类。
 */
public abstract class WxPayReply {
  @JacksonXmlProperty(localName = "return_code")
  protected String returnCode;
  @JacksonXmlProperty(localName = "return_msg")
  protected String returnMsg;
  @JacksonXmlProperty(localName = "appid")
  protected String appId;
  @JacksonXmlProperty(localName = "mch_id")
  protected String mchId;
  @JacksonXmlProperty(localName = "nonce_str")
  protected String nonceStr;
  @JacksonXmlProperty(localName = "sign")
  protected String sign;
  @JacksonXmlProperty(localName = "result_code")
  protected String resultCode;
  @JacksonXmlProperty(localName = "err_code")
  protected String errCode;
  @JacksonXmlProperty(localName = "err_code_des")
  protected String errCodeDes;

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getErrCode() {
    return errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getErrCodeDes() {
    return errCodeDes;
  }

  public void setErrCodeDes(String errCodeDes) {
    this.errCodeDes = errCodeDes;
  }
}

package coo.core.pay.wft;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.exception.BusinessException;

/**
 * 威富通支付响应基类。
 */
public abstract class WftPayReply {
  @XStreamAlias("version")
  protected String version;
  @XStreamAlias("charset")
  protected String charset;
  @XStreamAlias("sign_type")
  protected String signType;
  @XStreamAlias("status")
  protected String status;
  @XStreamAlias("message")
  protected String message;
  @XStreamAlias("result_code")
  protected String resultCode;
  @XStreamAlias("mch_id")
  protected String mchId;
  @XStreamAlias("device_info")
  private String deviceInfo;
  @XStreamAlias("nonce_str")
  protected String nonceStr;
  @XStreamAlias("err_code")
  protected String errCode;
  @XStreamAlias("err_msg")
  protected String errMsg;
  @XStreamAlias("sign")
  protected String sign;

  /**
   * 解析XML填充响应对象。
   * 
   * @param xml XML
   * @param key 密钥
   */
  public void parseXml(String xml, String key) {
    WftPayUtils.getXstream(getClass()).fromXML(xml, this);
    if (!sign.equals(WftPayUtils.sign(this, key))) {
      throw new BusinessException("威富通支付调用失败：验证签名失败。");
    }
    if (!"0".equals(status)) {
      throw new BusinessException("威富通支付调用失败：" + message);
    }
    if (resultCode != null && !"0".equals(resultCode)) {
      throw new BusinessException("威富通支付交易失败：" + errCode + "-" + errMsg);
    }
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }



  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getErrCode() {
    return errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}

package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.exception.BusinessException;

/**
 * 微信支付响应基类。
 */
public abstract class WxPayReply {
  @XStreamAlias("return_code")
  protected String returnCode;
  @XStreamAlias("return_msg")
  protected String returnMsg;
  @XStreamAlias("appid")
  protected String appId;
  @XStreamAlias("mch_id")
  protected String mchId;
  @XStreamAlias("nonce_str")
  protected String nonceStr;
  @XStreamAlias("sign")
  protected String sign;
  @XStreamAlias("result_code")
  protected String resultCode;
  @XStreamAlias("err_code")
  protected String errCode;
  @XStreamAlias("err_code_des")
  protected String errCodeDes;

  /**
   * 解析XML填充响应对象。
   * 
   * @param xml XML
   * @param key 密钥
   */
  public void parseXml(String xml, String key) {
    WxPayUtils.getXstream(getClass()).fromXML(xml, this);
    if (!sign.equals(WxPayUtils.sign(this, key))) {
      throw new BusinessException("微信支付调用失败：验证签名失败，返回数据为[" + xml + "]");
    }
    if ("FAIL".equals(returnCode)) {
      throw new BusinessException("微信支付调用失败：" + returnMsg);
    }
    if ("FAIL".equals(resultCode)) {
      throw new BusinessException("微信支付交易失败：" + errCode + "-" + errCodeDes);
    }
  }

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

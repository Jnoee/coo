package coo.core.pay.wx;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.exception.BusinessException;

/**
 * 微信支付响应基类。
 */
public abstract class WxPayReply extends WxPayData {
  /** 返回状态码 */
  @XStreamAlias("return_code")
  private String returnCode;
  /** 返回信息 */
  @XStreamAlias("return_msg")
  private String returnMsg;
  /** 业务结果 */
  @XStreamAlias("result_code")
  private String resultCode;
  /** 错误代码 */
  @XStreamAlias("err_code")
  private String errCode;
  /** 错误代码描述 */
  @XStreamAlias("err_code_des")
  private String errCodeDes;

  /**
   * 解析XML填充响应对象。
   * 
   * @param xml XML
   * @param key 密钥
   */
  public void parseXml(String xml, String key) {
    getXstream().fromXML(xml, this);
    if (!sign.equals(WxPayUtils.sign(this, key))) {
      throw new BusinessException("微信支付调用失败：验证签名失败。");
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

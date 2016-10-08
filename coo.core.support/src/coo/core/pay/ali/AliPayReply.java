package coo.core.pay.ali;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.exception.BusinessException;

/**
 * 支付宝支付响应。
 */
public abstract class AliPayReply {
  @XStreamAlias("is_success")
  private String isSuccess;
  @XStreamAlias("error")
  private String error;

  /**
   * 解析XML填充响应对象。
   * 
   * @param xml XML
   */
  public void parseXml(String xml) {
    AliPayUtils.getXstream(getClass()).fromXML(xml, this);
    if ("F".equals(isSuccess)) {
      throw new BusinessException("支付宝支付调用失败：" + error);
    }
  }

  public String getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(String isSuccess) {
    this.isSuccess = isSuccess;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}

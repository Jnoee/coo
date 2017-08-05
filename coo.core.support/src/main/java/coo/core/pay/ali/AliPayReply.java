package coo.core.pay.ali;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 支付宝支付响应。
 */
public abstract class AliPayReply {
  @JacksonXmlProperty(localName = "is_success")
  private String isSuccess;
  @JacksonXmlProperty(localName = "error")
  private String error;

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

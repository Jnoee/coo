package coo.mvc.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * API响应对象基类。
 */
@JacksonXmlRootElement(localName = "reply")
public class ApiReply {
  private String code;
  private String msg;

  /**
   * 构造方法。
   */
  public ApiReply() {
    this(ApiCode.SUCCESS, "操作成功。");
  }

  /**
   * 构造方法。
   * 
   * @param code 编码
   * @param msg 响应信息
   */
  public ApiReply(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}

package coo.mvc.api;

/**
 * API异常。
 */
public class ApiException extends RuntimeException {
  private static final long serialVersionUID = 3703190701308868615L;
  private String code;
  private String msg;

  /**
   * 构造方法。
   * 
   * @param code 编码
   * @param msg 异常信息
   */
  public ApiException(String code, String msg) {
    super(code + ":" + msg);
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

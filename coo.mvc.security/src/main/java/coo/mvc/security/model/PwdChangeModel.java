package coo.mvc.security.model;

/**
 * 修改密码表单Model。
 */
public class PwdChangeModel {
  /** 旧密码 */
  private String oldPwd;
  /** 新密码 */
  private String newPwd;
  /** 确认新密码 */
  private String confirmPwd;

  public String getOldPwd() {
    return oldPwd;
  }

  public void setOldPwd(String oldPwd) {
    this.oldPwd = oldPwd;
  }

  public String getNewPwd() {
    return newPwd;
  }

  public void setNewPwd(String newPwd) {
    this.newPwd = newPwd;
  }

  public String getConfirmPwd() {
    return confirmPwd;
  }

  public void setConfirmPwd(String confirmPwd) {
    this.confirmPwd = confirmPwd;
  }
}

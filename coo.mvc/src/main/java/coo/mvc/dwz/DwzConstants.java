package coo.mvc.dwz;

/**
 * DWZ结果状态常量。
 */
public class DwzConstants {
  public static final String OK = "200";
  public static final String ERROR = "300";
  public static final String TIMEOUT = "301";
  public static final String MSG_OK = "操作成功。";
  public static final String MSG_TIMEOUT = "您还没有登录或会话已过期，请重新登录。";
  public static final String MSG_DENIED = "您没有执行该操作的权限，请与管理员联系。";
  public static final String MSG_FAIL = "服务器暂时繁忙，请稍候重试或与管理员联系。";

  /**
   * 私有构造方法。
   */
  private DwzConstants() {}
}

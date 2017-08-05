package coo.core.sms;

/**
 * 短信组件接口。
 */
public interface SmsSender {
  /**
   * 发送及时短信。
   * 
   * @param payload 短信载体
   */
  public void send(SmsPayload payload);

  /**
   * 发送定时短信。
   * 
   * @param payload 短信载体
   */
  public void schedule(SmsSchedulePayload payload);
}

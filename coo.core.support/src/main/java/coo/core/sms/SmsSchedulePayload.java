package coo.core.sms;

import java.util.Date;

/**
 * 定时短信载体。
 */
public class SmsSchedulePayload extends SmsPayload {
  private Date sendTime;

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }
}

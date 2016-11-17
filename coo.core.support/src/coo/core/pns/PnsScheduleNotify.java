package coo.core.pns;

import java.util.Date;

/**
 * 定时推送通知。
 */
public class PnsScheduleNotify extends PnsNotify {
  /** 定时任务名称 */
  private String taskName;
  /** 推送时间 */
  private Date sendTime;

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }
}

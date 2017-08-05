package coo.core.sms;

import java.util.List;

/**
 * 短信载体。
 */
public class SmsPayload {
  private String tag;
  private String content;
  private List<String> mobiles;

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<String> getMobiles() {
    return mobiles;
  }

  public void setMobiles(List<String> mobiles) {
    this.mobiles = mobiles;
  }
}

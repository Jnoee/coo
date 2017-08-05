package coo.core.pns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 推送通知。
 */
public class PnsNotify {
  /** 标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 声音 */
  private String sound = "default";
  /** 扩展参数 */
  private Map<String, String> extras = new HashMap<>();
  /** 别名列表 */
  private List<String> aliases = new ArrayList<>();
  /** 设备列表 */
  private List<String> devices = new ArrayList<>();
  /** 标签列表 */
  private List<String> tags = new ArrayList<>();

  /**
   * 添加扩展参数。
   * 
   * @param key 键
   * @param value 值
   */
  public void addExtra(String key, String value) {
    extras.put(key, value);
  }

  /**
   * 添加别名。
   * 
   * @param alias 别名
   */
  public void addAlias(String alias) {
    aliases.add(alias);
  }

  /**
   * 添加设备。
   * 
   * @param device 设备
   */
  public void addDevice(String device) {
    devices.add(device);
  }

  /**
   * 添加标签。
   * 
   * @param tag 标签
   */
  public void addTag(String tag) {
    tags.add(tag);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSound() {
    return sound;
  }

  public void setSound(String sound) {
    this.sound = sound;
  }

  public Map<String, String> getExtras() {
    return extras;
  }

  public void setExtras(Map<String, String> extras) {
    this.extras = extras;
  }

  public List<String> getAliases() {
    return aliases;
  }

  public void setAliases(List<String> aliases) {
    this.aliases = aliases;
  }

  public List<String> getDevices() {
    return devices;
  }

  public void setDevices(List<String> devices) {
    this.devices = devices;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}

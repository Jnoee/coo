package coo.core.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件。
 */
public class Mail {
  /** 标题 */
  protected String subject;
  /** 正文 */
  protected String text;
  /** 发送方邮件地址 */
  protected String from;
  /** 回复邮件地址 */
  protected String replyTo;
  /** 收件方邮件地址列表 */
  protected List<String> to = new ArrayList<String>();
  /** 抄送方邮件地址列表 */
  protected List<String> cc = new ArrayList<String>();
  /** 秘密抄送方邮件地址列表 */
  protected List<String> bcc = new ArrayList<String>();
  /** 附件 */
  protected List<File> attachements = new ArrayList<File>();

  /**
   * 新增发送方邮件地址。
   * 
   * @param mails 邮件地址
   */
  public void addTo(String... mails) {
    for (String mail : mails) {
      to.add(mail);
    }
  }

  /**
   * 新增抄送方邮件地址。
   * 
   * @param mails 邮件地址
   */
  public void addCc(String... mails) {
    for (String mail : mails) {
      cc.add(mail);
    }
  }

  /**
   * 新增秘密抄送方邮件地址。
   * 
   * @param mails 邮件地址
   */
  public void addBcc(String... mails) {
    for (String mail : mails) {
      bcc.add(mail);
    }
  }

  /**
   * 新增附件。
   * 
   * @param attachement 附件
   */
  public void addAttachement(File attachement) {
    attachements.add(attachement);
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(String replyTo) {
    this.replyTo = replyTo;
  }

  public List<String> getTo() {
    return to;
  }

  public void setTo(List<String> to) {
    this.to = to;
  }

  public List<String> getCc() {
    return cc;
  }

  public void setCc(List<String> cc) {
    this.cc = cc;
  }

  public List<String> getBcc() {
    return bcc;
  }

  public void setBcc(List<String> bcc) {
    this.bcc = bcc;
  }

  public List<File> getAttachements() {
    return attachements;
  }

  public void setAttachements(List<File> attachements) {
    this.attachements = attachements;
  }
}

package coo.core.mail;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.base.util.CollectionUtils;
import coo.core.config.MailProperties;

/**
 * 邮件组件。
 */
public class MailSender extends JavaMailSenderImpl {
  private final Logger log = LoggerFactory.getLogger(getClass());

  /**
   * 构造方法。
   * 
   * @param properties 邮件配置属性
   */
  public MailSender(MailProperties properties) {
    BeanUtils.copyFields(properties, this);
    getJavaMailProperties().put("mail.smtp.auth", properties.getSmtp().getAuth());
    getJavaMailProperties().put("mail.smtp.starttls.enable",
        properties.getSmtp().getStarttlsEnable().toString());
    getJavaMailProperties().put("mail.smtp.timeout", properties.getSmtp().getTimeout().toString());
    getJavaMailProperties().put("mail.debug", properties.getDebug().toString());
  }

  /**
   * 发送邮件。
   * 
   * @param mail 邮件
   */
  public void send(Mail mail) {
    try {
      if (CollectionUtils.isEmpty(mail.getTo())) {
        log.warn("发送邮件时，收件人邮件地址列表为空。");
        return;
      }
      MimeMessageHelper helper = new MimeMessageHelper(createMimeMessage(), true, Encoding.UTF_8);
      helper.setSubject(mail.getSubject());
      helper.setFrom(mail.getFrom());
      helper.setTo(mail.getTo().toArray(new String[] {}));
      helper.setCc(mail.getCc().toArray(new String[] {}));
      helper.setBcc(mail.getBcc().toArray(new String[] {}));
      helper.setText(mail.getText(), true);

      for (File attachement : mail.getAttachements()) {
        helper.addAttachment(attachement.getName(), attachement);
      }
      send(helper.getMimeMessage());
    } catch (Exception e) {
      log.error("发送邮件时发生异常。", e);
      throw new UncheckedException("发送邮件时发生异常。", e);
    }
  }
}

package coo.core.mail;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import coo.core.config.CoreConfiguration;
import coo.core.config.SupportConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CoreConfiguration.class, SupportConfiguration.class})
public class MailSenderTest {
  private Logger log = LoggerFactory.getLogger(getClass());
  @Resource
  private MailSender mailSender;

  @Test
  public void testSendMail() throws Exception {
    Mail mail = new Mail();
    mail.setFrom("coo_mail_test@163.com");
    mail.addTo("song.he@warmeden.com");
    mail.setSubject("文本邮件" + System.currentTimeMillis());
    mail.setText("这是一封测试邮件。");
    log.debug(mail.getText());
    mailSender.send(mail);
  }

  // @Test
  public void testSendTemplateMail() throws Exception {
    TemplateMail templateMail = new TemplateMail();
    templateMail.setFrom("coo_mail_test@163.com");
    templateMail.addTo("song.he@warmeden.com");
    templateMail.setSubject("模版邮件" + System.currentTimeMillis());
    templateMail.setTemplateName("test-mail.ftl");
    templateMail.setVar("name", "coo");

    File attachement = new File("test/coo/core/mail/测试附件.txt");
    templateMail.addAttachement(attachement);

    mailSender.send(templateMail);
  }
}

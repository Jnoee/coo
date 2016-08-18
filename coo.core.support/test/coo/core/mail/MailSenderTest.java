package coo.core.mail;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mailTestContext.xml"})
public class MailSenderTest {
  @Resource
  private MailSender mailService;

  // @Test
  public void testSendMail() throws Exception {
    Mail mail = new Mail();
    mail.setFrom("coo_mail_test@163.com");
    mail.addTo("song.he@warmeden.com");
    mail.setSubject("文本邮件" + System.currentTimeMillis());
    mail.setText("这是一封测试邮件。");
    mailService.send(mail);
  }

  @Test
  public void testSendTemplateMail() throws Exception {
    TemplateMail templateMail = new TemplateMail();
    templateMail.setFrom("coo_mail_test@163.com");
    templateMail.addTo("song.he@warmeden.com");
    templateMail.setSubject("模版邮件" + System.currentTimeMillis());
    templateMail.setTemplateName("test-mail.ftl");
    templateMail.setVar("name", "coo");

    File attachement = new File("test/coo/core/mail/测试附件.txt");
    templateMail.addAttachement(attachement);

    mailService.send(templateMail);
  }
}

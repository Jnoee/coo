package coo.core.mail;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mailSenderTestContext.xml" })
public class MailSenderTest {
	@Resource
	private MailSender mailService;

	@Test
	public void testSendMail() throws Exception {
		Mail mail = new Mail();
		mail.setFrom("coo_mail_test@163.com");
		mail.addTo("hesong.sz@mopon.cn");
		mail.setSubject("测试邮件" + Math.random());
		mail.setText("这是一封测试邮件。");
		File attachement = new File("test/coo/core/mail/测试附件.txt");
		mail.addAttachement(attachement);
		mailService.send(mail);
	}

	@Test
	public void testSendTemplateMail() throws Exception {
		TemplateMail templateMail = new TemplateMail();
		templateMail.setFrom("coo_mail_test@163.com");
		templateMail.addTo("hesong.sz@mopon.cn");
		templateMail.setSubject("测试邮件");
		templateMail.setTemplateName("test-mail.ftl");
		templateMail.setVar("name", "coo");

		File attachement = new File("test/coo/core/mail/测试附件.txt");
		templateMail.addAttachement(attachement);

		mailService.sendAsync(templateMail);
		Thread.sleep(30000);
	}
}

package coo.core.mail;

import java.io.File;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;

/**
 * 邮件组件。
 */
@Component
public class MailService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private TaskExecutor taskExecutor;

	/**
	 * 同步发送邮件。
	 * 
	 * @param mail
	 *            邮件
	 */
	public void send(Mail mail) {
		try {
			MimeMessageHelper helper = new MimeMessageHelper(
					mailSender.createMimeMessage(), true, Encoding.UTF_8);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo().toArray(new String[] {}));
			helper.setCc(mail.getCc().toArray(new String[] {}));
			helper.setBcc(mail.getBcc().toArray(new String[] {}));
			helper.setText(mail.getText(), true);

			for (File attachement : mail.getAttachements()) {
				helper.addAttachment(attachement.getName(), attachement);
			}
			mailSender.send(helper.getMimeMessage());
		} catch (Exception e) {
			throw new UncheckedException("发送邮件时发生异常。", e);
		}
	}

	/**
	 * 异步发送邮件。
	 * 
	 * @param mail
	 *            邮件
	 */
	public void sendAsync(final Mail mail) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					send(mail);
				} catch (Exception e) {
					log.error("发送邮件时发生异常。", e);
				}
			}
		});
	}
}

package coo.core.mail;

import java.io.File;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;
import coo.base.util.CollectionUtils;

/**
 * 邮件组件。
 */
@Component
public class MailSender {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private JavaMailSender javaMailSender;

	/**
	 * 异步发送邮件。
	 * 
	 * @param mail
	 *            邮件
	 */
	@Async
	public void sendAsync(Mail mail) {
		send(mail);
	}

	/**
	 * 同步发送邮件。
	 * 
	 * @param mail
	 *            邮件
	 */
	public void send(Mail mail) {
		try {
			// 如果收件方邮件地址列表为空，直接返回。
			if (CollectionUtils.isEmpty(mail.getTo())) {
				log.warn("发送邮件时，收件人邮件地址列表为空。");
				return;
			}
			MimeMessageHelper helper = new MimeMessageHelper(
					javaMailSender.createMimeMessage(), true, Encoding.UTF_8);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo().toArray(new String[] {}));
			helper.setCc(mail.getCc().toArray(new String[] {}));
			helper.setBcc(mail.getBcc().toArray(new String[] {}));
			helper.setText(mail.getText(), true);

			for (File attachement : mail.getAttachements()) {
				helper.addAttachment(attachement.getName(), attachement);
			}
			javaMailSender.send(helper.getMimeMessage());
		} catch (Exception e) {
			log.error("发送邮件时发生异常。", e);
			throw new UncheckedException("发送邮件时发生异常。", e);
		}
	}
}
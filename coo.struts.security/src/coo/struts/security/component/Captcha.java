package coo.struts.security.component;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import coo.core.captcha.CaptchaImageConfig;
import coo.core.captcha.CaptchaImageGenerator;

/**
 * 验证码组件。
 */
@Component
@Scope("session")
public class Captcha implements Serializable {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private CaptchaImageGenerator captchaImageGenerator;
	@Resource
	private CaptchaImageConfig captchaImageConfig;
	/** 正确的验证码 */
	private String correctCode;
	/** 待验证的验证码 */
	private String code;

	/**
	 * 生成验证码图片。
	 * 
	 * @return 返回验证码图片。
	 */
	public BufferedImage generateImage() {
		correctCode = generateCode();
		log.debug("生成验证码: " + correctCode);
		return captchaImageGenerator.generateImage(correctCode);
	}

	/**
	 * 验证验证码，忽略大小写。
	 * 
	 * @return 正确返回true，错误返回false。
	 */
	public Boolean validate() {
		return code.equalsIgnoreCase(correctCode);
	}

	/**
	 * 验证验证码，验证大小写。
	 * 
	 * @return 正确返回true，错误返回false。
	 */
	public Boolean validateWithCase() {
		return code.equalsIgnoreCase(correctCode);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 生成随机验证码字符串。
	 * 
	 * @return 返回随机验证码字符串。
	 */
	private String generateCode() {
		char[] chars = captchaImageConfig.getChars().toCharArray();
		StringBuffer challengeString = new StringBuffer();
		for (int i = 0; i < captchaImageConfig.getLength(); i++) {
			double randomValue = Math.random();
			int randomIndex = (int) Math
					.round(randomValue * (chars.length - 1));
			char characterToShow = chars[randomIndex];
			challengeString.append(characterToShow);
		}
		return challengeString.toString();
	}
}

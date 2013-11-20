package coo.core.captcha;

import java.awt.Color;
import java.awt.Font;

import org.springframework.stereotype.Component;

/**
 * 验证码图片配置组件。该组件设置验证码图片的生成规则，可通过xml配置同名组件来覆盖默认配置。
 */
@Component
public class CaptchaImageConfig {
	/** 背景颜色 */
	private Color color = new Color(0xf5, 0xf5, 0xf5);
	/** 字体 */
	private Font font = new Font("Arial", Font.PLAIN, 20);

	/** 验证码图片宽度 */
	private Integer width = 70;
	/** 验证码图片高度 */
	private Integer height = 25;
	/** 验证码图片显示的字符个数 */
	private Integer length = 4;
	/** 干扰信息个数 */
	private Integer noise = 1;
	/** 随机字符 */
	private String chars = "abcdefghijklmnopqrstuvwxyz0123456789";

	/** 左右留白的边距 */
	private Float margin = 20.0f;
	/** 字符旋转范围(角度) */
	private Double rotation = 0.2;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getNoise() {
		return noise;
	}

	public void setNoise(Integer noise) {
		this.noise = noise;
	}

	public String getChars() {
		return chars;
	}

	public void setChars(String chars) {
		this.chars = chars;
	}

	public Float getMargin() {
		return margin;
	}

	public void setMargin(Float margin) {
		this.margin = margin;
	}

	public Double getRotation() {
		return rotation;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}
}

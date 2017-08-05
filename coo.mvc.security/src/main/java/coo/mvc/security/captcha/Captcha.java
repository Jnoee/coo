package coo.mvc.security.captcha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import coo.mvc.security.config.CaptchaProperties;

/**
 * 验证码组件。
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Captcha implements Serializable {
  private static final long serialVersionUID = -2712035875690565914L;
  private final transient Logger log = LoggerFactory.getLogger(getClass());
  private transient CaptchaProperties properties;
  /** 验证码图片 */
  private transient BufferedImage image;
  /** 正确的验证码 */
  private String correctCode;

  public Captcha(CaptchaProperties properties) {
    this.properties = properties;
  }

  /**
   * 获取验证码图片。
   * 
   * @return 返回验证码图片。
   */
  public BufferedImage getImage() {
    return image;
  }

  /**
   * 生成验证码图片。
   */
  public void generateImage() {
    correctCode = generateCode();
    image = generateImage(correctCode);
    log.debug("生成验证码[{}]。", correctCode);
  }

  /**
   * 验证验证码，忽略大小写。
   * 
   * @param code 验证码
   * 
   * @return 正确返回true，错误返回false。
   */
  public Boolean validate(String code) {
    if (code != null) {
      return code.equalsIgnoreCase(correctCode);
    }
    return false;
  }

  /**
   * 验证验证码，区分大小写。
   * 
   * @param code 验证码
   * 
   * @return 正确返回true，错误返回false。
   */
  public Boolean validateWithCase(String code) {
    return code.equals(correctCode);
  }

  /**
   * 生成随机验证码字符串。
   * 
   * @return 返回随机验证码字符串。
   */
  private String generateCode() {
    char[] chars = properties.getChars().toCharArray();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < properties.getLength(); i++) {
      double randomValue = Math.random();
      int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
      char characterToShow = chars[randomIndex];
      builder.append(characterToShow);
    }
    return builder.toString();
  }

  /**
   * 根据验证码字符串生成图片。
   * 
   * @param code 验证码字符串
   * @return 返回生成的图片。
   */
  public BufferedImage generateImage(String code) {
    BufferedImage bufferedImage = new BufferedImage(properties.getWidth(), properties.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = bufferedImage.createGraphics();
    setBackgroudColor(graphics);
    setNoise(graphics);
    setCode(graphics, code);
    graphics.dispose();
    return bufferedImage;
  }

  /**
   * 设置图像的背景颜色。
   * 
   * @param graphics 图像
   */
  private void setBackgroudColor(Graphics2D graphics) {
    graphics.setColor(properties.getColor());
    graphics.fillRect(0, 0, properties.getWidth(), properties.getHeight());
  }

  /**
   * 设置图像的干扰信号。
   * 
   * @param graphics 图像
   */
  private void setNoise(Graphics2D graphics) {
    Random random = new Random();
    for (int i = 0; i < properties.getNoise(); i++) {
      int circleColor = 80 + random.nextInt() * 70;
      float circleLinewidth = 0.3f + random.nextFloat();
      graphics.setColor(new Color(circleColor, circleColor, circleColor));
      graphics.setStroke(new BasicStroke(circleLinewidth));
      int circleRadius = random.nextInt() * properties.getHeight() / 2;
      int circleX = random.nextInt() * properties.getWidth() - circleRadius;
      int circleY = random.nextInt() * properties.getHeight() - circleRadius;
      graphics.drawOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
    }
  }

  /**
   * 设置图像显示的字符。
   * 
   * @param graphics 图像
   * @param code 待显示的字符
   */
  private void setCode(Graphics2D graphics, String code) {
    graphics.setFont(properties.getFont());
    FontMetrics fontMetrics = graphics.getFontMetrics();
    int charDim = Math.max(fontMetrics.getMaxAdvance(), fontMetrics.getHeight());
    float spaceForLetters = -properties.getMargin() * 2 + properties.getWidth();
    float spacePerChar = spaceForLetters / (properties.getLength() - 1.0f);
    char[] allChars = code.toCharArray();
    for (int i = 0; i < allChars.length; i++) {
      char charToPrint = allChars[i];
      int charWidth = fontMetrics.charWidth(charToPrint);
      BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
      Graphics2D charGraphics = generateCharGraphics(charImage, charDim);
      int charX = (int) (0.5 * charDim - 0.5 * charWidth);
      charGraphics.drawString(Character.toString(charToPrint), charX,
          (charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent());
      float x = properties.getMargin() + spacePerChar * (i) - charDim / 2.0f;
      int y = (properties.getHeight() - charDim) / 2;
      graphics.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
      charGraphics.dispose();
    }
  }

  /**
   * 生成单个字符图像。
   * 
   * @param charImage 字符图片
   * @param charDim 字符尺寸
   * @return 返回字符图像。
   */
  private Graphics2D generateCharGraphics(BufferedImage charImage, int charDim) {
    Random random = new Random();
    int halfCharDim = charDim / 2;
    Graphics2D charGraphics = charImage.createGraphics();
    charGraphics.translate(halfCharDim, halfCharDim);
    double angle = (random.nextDouble() - 0.5) * properties.getRotation();
    charGraphics.transform(AffineTransform.getRotateInstance(angle));
    charGraphics.translate(-halfCharDim, -halfCharDim);
    int charColor = 60 + random.nextInt() * 90;
    charGraphics.setColor(new Color(charColor, charColor, charColor));
    charGraphics.setFont(properties.getFont());
    return charGraphics;
  }
}

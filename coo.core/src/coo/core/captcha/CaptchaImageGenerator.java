package coo.core.captcha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 用于生成验证码图片的组件。它根据CaptchaImageConfig组件的配置规则来生成验证码图片。
 */
@Component
public class CaptchaImageGenerator {
  @Resource
  private CaptchaImageConfig captchaImageConfig;

  /**
   * 根据验证码字符串生成图片。
   * 
   * @param code 验证码字符串
   * @return 返回生成的图片。
   */
  public BufferedImage generateImage(String code) {
    BufferedImage bufferedImage = new BufferedImage(captchaImageConfig.getWidth(),
        captchaImageConfig.getHeight(), BufferedImage.TYPE_INT_RGB);
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
    graphics.setColor(captchaImageConfig.getColor());
    graphics.fillRect(0, 0, captchaImageConfig.getWidth(), captchaImageConfig.getHeight());
  }

  /**
   * 设置图像的干扰信号。
   * 
   * @param graphics 图像
   */
  private void setNoise(Graphics2D graphics) {
    for (int i = 0; i < captchaImageConfig.getNoise(); i++) {
      int circleColor = 80 + (int) (Math.random() * 70);
      float circleLinewidth = 0.3f + (float) (Math.random());
      graphics.setColor(new Color(circleColor, circleColor, circleColor));
      graphics.setStroke(new BasicStroke(circleLinewidth));
      int circleRadius = (int) (Math.random() * captchaImageConfig.getHeight() / 2.0);
      int circleX = (int) (Math.random() * captchaImageConfig.getWidth() - circleRadius);
      int circleY = (int) (Math.random() * captchaImageConfig.getHeight() - circleRadius);
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
    graphics.setFont(captchaImageConfig.getFont());
    FontMetrics fontMetrics = graphics.getFontMetrics();
    int charDim = Math.max(fontMetrics.getMaxAdvance(), fontMetrics.getHeight());
    float spaceForLetters = -captchaImageConfig.getMargin() * 2 + captchaImageConfig.getWidth();
    float spacePerChar = spaceForLetters / (captchaImageConfig.getLength() - 1.0f);
    char[] allChars = code.toCharArray();
    for (int i = 0; i < allChars.length; i++) {
      char charToPrint = allChars[i];
      int charWidth = fontMetrics.charWidth(charToPrint);
      BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
      Graphics2D charGraphics = generateCharGraphics(charImage, charDim);
      int charX = (int) (0.5 * charDim - 0.5 * charWidth);
      charGraphics.drawString("" + charToPrint, charX,
          (charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent());
      float x = captchaImageConfig.getMargin() + spacePerChar * (i) - charDim / 2.0f;
      int y = (captchaImageConfig.getHeight() - charDim) / 2;
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
    int halfCharDim = charDim / 2;
    Graphics2D charGraphics = charImage.createGraphics();
    charGraphics.translate(halfCharDim, halfCharDim);
    double angle = (Math.random() - 0.5) * captchaImageConfig.getRotation();
    charGraphics.transform(AffineTransform.getRotateInstance(angle));
    charGraphics.translate(-halfCharDim, -halfCharDim);
    int charColor = 60 + (int) (Math.random() * 90);
    charGraphics.setColor(new Color(charColor, charColor, charColor));
    charGraphics.setFont(captchaImageConfig.getFont());
    return charGraphics;
  }
}

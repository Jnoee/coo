package coo.core.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;
import coo.base.util.CryptoUtils;

/**
 * 二维码解码器。
 */
public class QrDecoder {
  /**
   * 获取二维码内容。
   * 
   * @param image 二维码图片
   * @return 返回二维码内容。
   */
  public static String decode(BufferedImage image) {
    try {
      LuminanceSource source = new BufferedImageLuminanceSource(image);
      BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
      Result result = new MultiFormatReader().decode(bitmap, getHints());
      return result.getText();
    } catch (Exception e) {
      throw new UncheckedException("解析二维码时发生异常。", e);
    }
  }

  /**
   * 获取二维码内容。
   * 
   * @param file 二维码文件
   * @return 返回二维码内容。
   */
  public static String decode(File file) {
    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      return decode(bufferedImage);
    } catch (Exception e) {
      throw new UncheckedException("解析二维码时发生异常。", e);
    }
  }

  /**
   * 获取二维码内容。
   * 
   * @param code 二维码图片BASE64编码字符串
   * @return 返回二维码内容。
   */
  public static String decode(String code) {
    try {
      byte[] bytes = CryptoUtils.decodeBase64(code.getBytes(Encoding.UTF_8));
      ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
      BufferedImage bufferedImage = ImageIO.read(stream);
      return decode(bufferedImage);
    } catch (Exception e) {
      throw new UncheckedException("解析二维码时发生异常。", e);
    }
  }

  /**
   * 获取二维码配置参数。
   * 
   * @return 返回二维码配置参数。
   */
  private static EnumMap<DecodeHintType, Object> getHints() {
    EnumMap<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
    hints.put(DecodeHintType.CHARACTER_SET, Encoding.UTF_8);
    hints.put(DecodeHintType.PURE_BARCODE, true);
    return hints;
  }

  /**
   * 私有构造方法。
   */
  private QrDecoder() {}
}

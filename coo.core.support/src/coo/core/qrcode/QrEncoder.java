package coo.core.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import coo.base.constants.Encoding;
import coo.base.exception.UncheckedException;
import coo.base.util.CryptoUtils;
import coo.base.util.FileUtils;

/**
 * 二维码编码器。
 */
public class QrEncoder {
  /**
   * 生成指定内容的二维码并写入文件。
   * 
   * @param content 内容
   * @param file 文件
   * @param size 图片尺寸（像素）
   */
  public static void write(String content, Path file, Integer size) {
    try {
      String format = FileUtils.getFileType(file.toFile().getName());
      BitMatrix matrix =
          new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, getHints());
      MatrixToImageWriter.writeToPath(matrix, format, file);
    } catch (Exception e) {
      throw new UncheckedException("生成二维码时发生异常。", e);
    }
  }

  /**
   * 生成指定内容的二维码并写入输出流。
   * 
   * @param content 内容
   * @param stream 输出流
   * @param format 图片格式
   * @param size 图片尺寸（像素）
   */
  public static void write(String content, OutputStream stream, String format, Integer size) {
    try {
      BitMatrix matrix =
          new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, getHints());
      MatrixToImageWriter.writeToStream(matrix, format, stream);
    } catch (Exception e) {
      throw new UncheckedException("生成二维码时发生异常。", e);
    }
  }

  /**
   * 生成指定内容的二维码图片。
   * 
   * @param content 内容
   * @param size 图片尺寸（像素）
   * @return 返回生成的二维码图片。
   */
  public static BufferedImage image(String content, Integer size) {
    try {
      BitMatrix matrix =
          new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, getHints());
      return MatrixToImageWriter.toBufferedImage(matrix);
    } catch (Exception e) {
      throw new UncheckedException("生成二维码时发生异常。", e);
    }
  }

  /**
   * 生成指定内容的二维码图片的BASE64编码字符串。
   * 
   * @param content 内容
   * @param format 图片格式
   * @param size 图片尺寸（像素）
   * @return 返回生成的二维码图片的BASE64编码字符串。
   */
  public static String base64(String content, String format, Integer size) {
    try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      write(content, stream, format, size);
      return new String(CryptoUtils.encodeBase64(stream.toByteArray()));
    } catch (Exception e) {
      throw new UncheckedException("生成二维码时发生异常。", e);
    }
  }

  /**
   * 获取二维码配置参数。
   * 
   * @return 返回二维码配置参数。
   */
  private static Hashtable<EncodeHintType, Object> getHints() {
    Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
    hints.put(EncodeHintType.CHARACTER_SET, Encoding.UTF_8);
    return hints;
  }
}

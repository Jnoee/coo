package coo.base.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import coo.base.exception.UncheckedException;

/**
 * 图片操作工具类。
 */
public class ImageUtils {
  /**
   * 读取图片。当图片文件是非法格式（如CMYK色彩的JPEG文件或者修改了后缀的其它文件）时返回格式错误提示图片。
   * 
   * @param imageFile 图片文件
   * @return 返回图片。
   */
  public static BufferedImage readImage(File imageFile) {
    if (isImage(imageFile)) {
      try {
        return ImageIO.read(imageFile);
      } catch (IOException e) {
        return getNoneImage();
      }
    } else {
      return getNoneImage();
    }
  }

  /**
   * 判断文件是否为图片文件。
   * 
   * @param file 文件
   * @return 如果文件为图片文件返回true，否则返回false。
   */
  public static Boolean isImage(File file) {
    Boolean isImage = false;
    try {
      ImageInputStream fileInput = ImageIO.createImageInputStream(file);
      Iterator<ImageReader> iter = ImageIO.getImageReaders(fileInput);
      isImage = iter.hasNext();
      fileInput.close();
    } catch (IOException e) {
      throw new UncheckedException("读取图片文件" + file.getName() + "时发生异常", e);
    }
    return isImage;
  }

  /**
   * 获取格式错误提示图片。
   * 
   * @return 返回格式错误提示图片。
   */
  public static BufferedImage getNoneImage() {
    try {
      InputStream in = ImageUtils.class.getResourceAsStream("none.jpg");
      BufferedImage image = ImageIO.read(in);
      in.close();
      return image;
    } catch (IOException e) {
      throw new UncheckedException("读取图片文件none.jpg时发生异常", e);
    }
  }

  /**
   * 私有构造方法。
   */
  private ImageUtils() {}
}

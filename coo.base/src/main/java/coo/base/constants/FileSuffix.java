package coo.base.constants;

/**
 * 常用文件后缀名常量。
 */
public class FileSuffix {
  public static final String TXT = "*.txt";
  public static final String DOC = "*.doc;*.docx";
  public static final String XLS = "*.xls";
  public static final String PPT = "*.ppt";
  public static final String PDF = "*.pdf";
  public static final String OFCS = TXT + ";" + DOC + ";" + XLS + ";" + PPT + ";" + PDF;

  public static final String JPG = "*.jpg;*.jpeg";
  public static final String GIF = "*.gif";
  public static final String PNG = "*.png";
  public static final String BMP = "*.bmp";
  public static final String IMGS = JPG + ";" + GIF + ";" + PNG + ";" + BMP;

  public static final String HTML = "*.html;*.htm";
  public static final String EXE = "*.exe";

  public static final String ZIP = "*.zip";
  public static final String RAR = "*.rar";
  public static final String ZIPS = ZIP + ";" + RAR;

  public static final String SWF = "*.swf";
  public static final String MP3 = "*.mp3";
  public static final String RAM = "*.ram";
  public static final String AVI = "*.avi";

  /**
   * 私有构造方法。
   */
  private FileSuffix() {}
}

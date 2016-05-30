package coo.core.xstream;

import java.util.Date;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import coo.base.util.DateUtils;

/**
 * 日期转换器。
 */
public class DateConverter extends AbstractSingleValueConverter {
  private String format = DateUtils.SECOND;

  /**
   * 构造方法。
   * 
   * @param format 日期格式
   */
  public DateConverter(String format) {
    this.format = format;
  }

  /**
   * 默认构造方法。
   */
  public DateConverter() {}

  @SuppressWarnings("rawtypes")
  @Override
  public boolean canConvert(Class type) {
    return Date.class.isAssignableFrom(type);
  }

  @Override
  public String toString(Object obj) {
    return DateUtils.format((Date) obj, format);
  }

  @Override
  public Object fromString(String str) {
    return DateUtils.parse(str, format);
  }
}

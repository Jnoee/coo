package coo.base.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字操作工具类。
 */
public class NumberUtils {
  /**
   * 对一个整数按指定长度补零。
   * 
   * @param num 整数
   * @param length 长度
   * @return 返回补零后的字符串。
   */
  public static String fillZero(Integer num, Integer length) {
    char[] chs = new char[length];
    for (int i = 0; i < length; i++) {
      chs[i] = '0';
    }
    DecimalFormat df = new DecimalFormat(new String(chs));
    return df.format(num);
  }

  /**
   * 四舍五入保留两位小数。
   * 
   * @param value 值
   * @return 返回四舍五入后的值。
   */
  public static Double halfUp(Double value) {
    return halfUp(value, 2);
  }

  /**
   * 四舍五入。
   * 
   * @param value 值
   * @param scale 保留小数位数
   * @return 返回四舍五入后的值。
   */
  public static Double halfUp(Double value, Integer scale) {
    BigDecimal bValue = new BigDecimal(value.toString());
    return bValue.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * 四舍五入（保留两位小数）。
   * 
   * @param value 值
   * @return 返回四舍五入后的值。
   */
  public static Float halfUp(Float value) {
    return halfUp(value, 2);
  }

  /**
   * 四舍五入。
   * 
   * @param value 值
   * @param scale 保留小数位数
   * @return 返回四舍五入后的值。
   */
  public static Float halfUp(Float value, Integer scale) {
    BigDecimal bValue = new BigDecimal(value.toString());
    return bValue.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
  }

  /**
   * 加（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相加后的值。
   */
  public static Double add(Double value1, Double value2) {
    return add(value1, value2, 2);
  }

  /**
   * 加。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相加后的值。
   */
  public static Double add(Double value1, Double value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return halfUp(bValue1.add(bValue2).doubleValue(), scale);
  }

  /**
   * 加（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相加后的值。
   */
  public static Float add(Float value1, Float value2) {
    return add(value1, value2, 2);
  }

  /**
   * 加。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相加后的值。
   */
  public static Float add(Float value1, Float value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return halfUp(bValue1.add(bValue2).floatValue(), scale);
  }

  /**
   * 减（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相减后的值。
   */
  public static Double sub(Double value1, Double value2) {
    return sub(value1, value2, 2);
  }

  /**
   * 减。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相减后的值。
   */
  public static Double sub(Double value1, Double value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return halfUp(bValue1.subtract(bValue2).doubleValue(), scale);
  }

  /**
   * 减（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相减后的值。
   */
  public static Float sub(Float value1, Float value2) {
    return sub(value1, value2, 2);
  }

  /**
   * 减。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相减后的值。
   */
  public static Float sub(Float value1, Float value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return halfUp(bValue1.subtract(bValue2).floatValue(), scale);
  }

  /**
   * 乘（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相乘后的值。
   */
  public static Double mul(Double value1, Double value2) {
    return mul(value1, value2, 2);
  }

  /**
   * 乘。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相乘后的值。
   */
  public static Double mul(Double value1, Double value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return halfUp(bValue1.multiply(bValue2).doubleValue(), scale);
  }

  /**
   * 乘（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相乘后的值。
   */
  public static Float mul(Float value1, Float value2) {
    return mul(value1, value2, 2);
  }

  /**
   * 乘。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相乘后的值。
   */
  public static Float mul(Float value1, Float value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return halfUp(bValue1.multiply(bValue2).floatValue(), scale);
  }

  /**
   * 除（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相除后的值。
   */
  public static Double div(Integer value1, Integer value2) {
    return div(value1.doubleValue(), value2.doubleValue());
  }

  /**
   * 除。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相除后的值。
   */
  public static Double div(Integer value1, Integer value2, Integer scale) {
    return div(value1.doubleValue(), value2.doubleValue(), scale);
  }

  /**
   * 除（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相除后的值。
   */
  public static Double div(Double value1, Double value2) {
    return div(value1, value2, 2);
  }

  /**
   * 除。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相除后的值。
   */
  public static Double div(Double value1, Double value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return bValue1.divide(bValue2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * 除（保留两位小数）。
   * 
   * @param value1 值1
   * @param value2 值2
   * @return 返回相除后的值。
   */
  public static Float div(Float value1, Float value2) {
    return div(value1, value2, 2);
  }

  /**
   * 除。
   * 
   * @param value1 值1
   * @param value2 值2
   * @param scale 保留小数位数
   * @return 返回相除后的值。
   */
  public static Float div(Float value1, Float value2, Integer scale) {
    BigDecimal bValue1 = new BigDecimal(value1.toString());
    BigDecimal bValue2 = new BigDecimal(value2.toString());
    return bValue1.divide(bValue2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
  }
}

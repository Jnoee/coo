package coo.base.util;

import java.math.BigDecimal;

/**
 * 数字操作工具类。
 */
public class NumberUtils {
	/**
	 * 四舍五入保留两位小数。
	 * 
	 * @param value
	 *            值
	 * @return 返回四舍五入后的值。
	 */
	public static Double halfUp(Double value) {
		return halfUp(value, 2);
	}

	/**
	 * 四舍五入。
	 * 
	 * @param value
	 *            值
	 * @param scale
	 *            保留小数位数
	 * @return 返回四舍五入后的值。
	 */
	public static Double halfUp(Double value, Integer scale) {
		BigDecimal bValue = new BigDecimal(value.toString());
		return bValue.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入保留两位小数。
	 * 
	 * @param value
	 *            值
	 * @return 返回四舍五入后的值。
	 */
	public static Float halfUp(Float value) {
		return halfUp(value, 2);
	}

	/**
	 * 四舍五入。
	 * 
	 * @param value
	 *            值
	 * @param scale
	 *            保留小数位数
	 * @return 返回四舍五入后的值。
	 */
	public static Float halfUp(Float value, Integer scale) {
		BigDecimal bValue = new BigDecimal(value.toString());
		return bValue.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 加。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相加后的值。
	 */
	public static Double add(Double value1, Double value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.add(bValue2).doubleValue();
	}

	/**
	 * 加。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相加后的值。
	 */
	public static Float add(Float value1, Float value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.add(bValue2).floatValue();
	}

	/**
	 * 减。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相减后的值。
	 */
	public static Double sub(Double value1, Double value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.subtract(bValue2).doubleValue();
	}

	/**
	 * 减。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相减后的值。
	 */
	public static Float sub(Float value1, Float value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.subtract(bValue2).floatValue();
	}

	/**
	 * 乘。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相乘后的值。
	 */
	public static Double mul(Double value1, Double value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.multiply(bValue2).doubleValue();
	}

	/**
	 * 乘。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相乘后的值。
	 */
	public static Float mul(Float value1, Float value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.multiply(bValue2).floatValue();
	}

	/**
	 * 除。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相除后的值。
	 */
	public static Double div(Double value1, Double value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.divide(bValue2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 除。
	 * 
	 * @param value1
	 *            值1
	 * @param value2
	 *            值2
	 * @return 返回相除后的值。
	 */
	public static Float div(Float value1, Float value2) {
		BigDecimal bValue1 = new BigDecimal(value1.toString());
		BigDecimal bValue2 = new BigDecimal(value2.toString());
		return bValue1.divide(bValue2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
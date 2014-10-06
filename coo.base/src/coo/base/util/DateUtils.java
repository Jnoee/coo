package coo.base.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期时间工具类。
 */
public class DateUtils {
	public static final String MONTH = "yyyy-MM";
	public static final String DAY = "yyyy-MM-dd";
	public static final String MINUTE = "yyyy-MM-dd HH:mm";
	public static final String SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String MILLISECOND = "yyyy-MM-dd HH:mm:ss SSSS";

	public static final String MONTH_N = "yyyyMM";
	public static final String DAY_N = "yyyyMMdd";
	public static final String MINUTE_N = "yyyyMMddHHmm";
	public static final String SECOND_N = "yyyyMMddHHmmss";
	public static final String MILLISECOND_N = "yyyyMMddHHmmssSSSS";

	public static final String[] FORMATS = new String[] { DAY, MONTH, MINUTE,
			SECOND, MILLISECOND, DAY_N, MONTH_N, MINUTE_N, SECOND_N,
			MILLISECOND_N };

	/**
	 * 将字符串解析成Date对象。<br/>
	 * 该方法尝试用[yyyy-MM/yyyy-MM-dd/ yyyy-MM-dd HH:mm/yyyy-MM-dd
	 * HH:mm:ss/yyyy-MM-dd HH:mm:ss SSSS/ yyyyMM/yyyyMMdd/yyyyMMddHHmm/
	 * yyyyMMddHHmmss/yyyyMMddHHmmssSSSS]格式进行解析，如果无法解析将抛出异常。
	 * 
	 * @param str
	 *            字符串
	 * @return 返回对应的Date对象。
	 */
	public static Date parse(String str) {
		String pattern = getDateFormat(str);
		return parse(str, pattern);
	}

	/**
	 * 将指定格式的字符串解析成Date对象。
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            格式
	 * @return 返回对应的Date对象。
	 */
	public static Date parse(String str, String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		return DateTime.parse(str, formatter).toDate();
	}

	/**
	 * 将Date对象解析成yyyy-MM-dd格式的字符串。
	 * 
	 * @param date
	 *            Date对象
	 * @return 返回yyyy-MM-dd格式的字符串。
	 */
	public static String format(Date date) {
		return format(date, DAY);
	}

	/**
	 * 将Date对象解析成指定格式的字符串。
	 * 
	 * @param date
	 *            Date对象
	 * @param pattern
	 *            格式
	 * @return 返回指定格式的字符串。
	 */
	public static String format(Date date, String pattern) {
		return new DateTime(date).toString(pattern);
	}

	/**
	 * 获取字符串的日期格式。如果字符串不在[{@link #MONTH}/{@link #DAY}/ {@link #MINUTE} /
	 * {@link #SECOND}/{@link #MILLISECOND} ]格式范围内将抛出异常。
	 * 
	 * @param str
	 *            字符串
	 * @return 返回字符串的日期格式。
	 */
	public static String getDateFormat(String str) {
		for (String format : FORMATS) {
			if (isDate(str, format)) {
				return format;
			}
		}
		throw new IllegalArgumentException("不支持的日期格式：" + str);
	}

	/**
	 * 判断字符串是否为日期格式的字符串。
	 * 
	 * @param str
	 *            字符串
	 * @return 如果是日期格式的字符串返回true，否则返回false。
	 */
	public static Boolean isDate(String str) {
		for (String format : FORMATS) {
			if (isDate(str, format)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否为指定日期格式的字符串。
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return 如果是指定日期格式的字符串返回true，否则返回false。
	 */
	public static Boolean isDate(String str, String format) {
		try {
			parse(str, format);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取当前日期（只取到日期，时间部分都为0）。
	 * 
	 * @return 返回当前日期。
	 */
	public static Date getToday() {
		return DateTime.now().toLocalDate().toDate();
	}

	/**
	 * 获取当前日期前一天日期。
	 * 
	 * @return 返回当前日期前一天日期。
	 */
	public static Date getPrevDay() {
		return getPrevDay(getToday());
	}

	/**
	 * 获取指定日期前一天日期。
	 * 
	 * @param date
	 *            指定日期
	 * @return 返回指定日期前一天日期。
	 */
	public static Date getPrevDay(Date date) {
		return getMinusDay(date, 1);
	}

	/**
	 * 获取当前日期后一天日期（只取到日期，时间部分都为0）。
	 * 
	 * @return 返回后一天日期。
	 */
	public static Date getNextDay() {
		return getNextDay(getToday());
	}

	/**
	 * 获取指定日期后一天日期。
	 * 
	 * @param date
	 *            指定日期
	 * @return 返回指定日期的后一天日期。
	 */
	public static Date getNextDay(Date date) {
		return getPlusDay(getToday(), 1);
	}

	/**
	 * 获取指定日期后几天日期。
	 * 
	 * @param date
	 *            指定日期
	 * @param days
	 *            后几天
	 * @return 返回指定日期后几天日期。
	 */
	public static Date getPlusDay(Date date, Integer days) {
		return new DateTime(date).plusDays(days).toLocalDate().toDate();
	}

	/**
	 * 获取指定日期前几天日期。
	 * 
	 * @param date
	 *            指定日期
	 * @param days
	 *            前几天
	 * @return 返回指定日期前几天日期。
	 */
	public static Date getMinusDay(Date date, Integer days) {
		return new DateTime(date).minusDays(days).toLocalDate().toDate();
	}

	/**
	 * 获取Joda Time的Duration对象。
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回Joda Time的Duration对象。
	 */
	public static Duration getDuration(Date beginDate, Date endDate) {
		return new Duration(new DateTime(beginDate), new DateTime(endDate));
	}

	/**
	 * 获取Joda Time的Peroid对象。
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回Joda Time的Peroid对象。
	 */
	public static Period getPeriod(Date beginDate, Date endDate) {
		return new Period(new DateTime(beginDate), new DateTime(endDate));
	}

	/**
	 * 获取Joda Time的Interval对象。
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回Joda Time的Interval对象。
	 */
	public static Interval getInterval(Date beginDate, Date endDate) {
		return new Interval(new DateTime(beginDate), new DateTime(endDate));
	}
}
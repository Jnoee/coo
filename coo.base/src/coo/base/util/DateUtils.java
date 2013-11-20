package coo.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;

import coo.base.exception.UncheckedException;

/**
 * 日期时间工具类。
 */
public class DateUtils {
	public static final String TO_MONTH = "yyyy-MM";
	public static final String TO_DATE = "yyyy-MM-dd";
	public static final String TO_MINUTE = "yyyy-MM-dd HH:mm";
	public static final String TO_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String TO_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSSS";

	public static final String[] FORMATS = new String[] { TO_DATE, TO_MONTH,
			TO_MINUTE, TO_SECOND, TO_MILLISECOND };

	public static final String TO_MONTH_N = "yyyyMM";
	public static final String TO_DATE_N = "yyyyMMdd";
	public static final String TO_MINUTE_N = "yyyyMMddHHmm";
	public static final String TO_SECOND_N = "yyyyMMddHHmmss";
	public static final String TO_MILLISECOND_N = "yyyyMMddHHmmssSSSS";

	/**
	 * 将字符串解析成Date对象。该方法尝试用[{@link #TO_MONTH}/{@link #TO_DATE}/
	 * {@link #TO_MINUTE}/{@link #TO_SECOND}/{@link #TO_MILLISECOND}
	 * ]格式进行解析，如果无法解析将抛出异常。<br/>
	 * 该方法遍历五种日期格式，效率较低。如果你明确知道字符串的日期格式应调用 {@link #parse(String, String)}方法。
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
		SimpleDateFormat parser = new SimpleDateFormat(format);
		try {
			Date date = parser.parse(str);
			// 如果格式中未指定年则填入当前年
			if (!format.contains("y")) {
				Calendar cal = Calendar.getInstance();
				int currentYear = cal.get(Calendar.YEAR);
				cal.setTime(date);
				cal.set(Calendar.YEAR, currentYear);
				date = cal.getTime();
			}
			return date;
		} catch (ParseException e) {
			throw new UncheckedException("将字符串解析为Date对象时发生异常", e);
		}
	}

	/**
	 * 将Date对象解析成yyyy-MM-dd格式的字符串。
	 * 
	 * @param date
	 *            Date对象
	 * @return 返回yyyy-MM-dd格式的字符串。
	 */
	public static String format(Date date) {
		return format(date, TO_DATE);
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
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		return formater.format(date);
	}

	/**
	 * 获取字符串的日期格式。如果字符串不在[{@link #TO_MONTH}/{@link #TO_DATE}/ {@link #TO_MINUTE}
	 * /{@link #TO_SECOND}/{@link #TO_MILLISECOND} ]格式范围内将抛出异常。
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
		} catch (UncheckedException e) {
			return false;
		}
	}

	/**
	 * 获取当前日期（只取到日期，时间部分都为0）。
	 * 
	 * @return 返回当前日期。
	 */
	public static Date getToday() {
		return parse(format(new Date()), TO_DATE);
	}

	/**
	 * 获取下一天日期。（只取到日期，时间部分都为0）。
	 * 
	 * @return 返回下一天日期。
	 */
	public static Date getNextDay() {
		return getNextDay(getToday());
	}

	/**
	 * 获取指定日期的下一天日期。
	 * 
	 * @param date
	 *            指定日期
	 * @return 返回指定日期的下一天日期。
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
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

package coo.core.hibernate.search;

import java.util.Date;
import java.util.Map;

import org.hibernate.search.bridge.ParameterizedBridge;
import org.hibernate.search.bridge.TwoWayStringBridge;

import coo.base.util.DateUtils;

/**
 * 日期字段全文索引桥接器。内置的日期桥接器使用Lucene的DateTools工具类进行时间转换，其中时区被固定设置为零时区，
 * 会导致创建的全文索引与实际时间发生偏差。
 */
public class DateBridge implements TwoWayStringBridge, ParameterizedBridge {
	private String format = DateUtils.MILLISECOND_N;

	@Override
	public String objectToString(Object object) {
		return DateUtils.format((Date) object, format);
	}

	@Override
	public Object stringToObject(String stringValue) {
		return DateUtils.parse(stringValue, format);
	}

	@Override
	public void setParameterValues(Map<String, String> parameters) {
		String format = parameters.get("format");
		if (format != null) {
			this.format = format;
		}
	}
}

package coo.core.xstream;

import java.util.Date;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import coo.base.util.DateUtils;

/**
 * 日期转换器。
 */
public class DateConverter extends AbstractSingleValueConverter {
	private String format;

	/**
	 * 构造方法。
	 * 
	 * @param format
	 *            日期格式
	 */
	public DateConverter(String format) {
		this.format = format;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(Date.class);
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
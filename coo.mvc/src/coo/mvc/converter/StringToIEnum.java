package coo.mvc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import coo.core.model.IEnum;
import coo.core.util.IEnumUtils;

/**
 * 字符串转换成IEnum转换器工厂。
 */
public class StringToIEnum implements ConverterFactory<String, IEnum> {
	@Override
	public <T extends IEnum> Converter<String, T> getConverter(
			Class<T> targetType) {
		return new StringToIEnumConverter<T>(targetType);
	}

	/**
	 * String转换成IEnum转换器
	 * 
	 * @param <T>
	 *            IEnum枚举类型
	 */
	private class StringToIEnumConverter<T extends IEnum> implements
			Converter<String, T> {
		private final Class<T> toClass;

		/**
		 * 构造方法。
		 * 
		 * @param toClass
		 *            转换目标类
		 */
		public StringToIEnumConverter(Class<T> toClass) {
			this.toClass = toClass;
		}

		/**
		 * 将字符串值转换为IEnum枚举对象。
		 * 
		 * @param source
		 *            字符串值
		 * @return 返回IEnum枚举对象。
		 */
		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			return (T) IEnumUtils.getIEnumByValue(toClass, source.trim());
		}
	}
}
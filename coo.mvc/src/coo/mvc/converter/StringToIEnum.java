package coo.mvc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import coo.core.model.IEnum;
import coo.core.util.IEnumUtils;

public class StringToIEnum implements ConverterFactory<String, IEnum> {
	@Override
	public <T extends IEnum> Converter<String, T> getConverter(
			Class<T> targetType) {
		return new StringToIEnumConverter<T>(targetType);
	}

	private class StringToIEnumConverter<T extends IEnum> implements
			Converter<String, T> {

		private final Class<T> toClass;

		public StringToIEnumConverter(Class<T> toClass) {
			this.toClass = toClass;
		}

		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			return (T) IEnumUtils.getIEnumByValue(toClass, source.trim());
		}
	}
}
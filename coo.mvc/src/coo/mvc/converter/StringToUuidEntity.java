package coo.mvc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import coo.core.model.UuidEntity;
import coo.core.util.SpringUtils;

public class StringToUuidEntity implements ConverterFactory<String, UuidEntity> {
	@Override
	public <T extends UuidEntity> Converter<String, T> getConverter(
			Class<T> targetType) {
		return new StringToUuidEntityConverter<T>(targetType);
	}

	private class StringToUuidEntityConverter<T extends UuidEntity> implements
			Converter<String, T> {

		private final Class<T> toClass;

		public StringToUuidEntityConverter(Class<T> toClass) {
			this.toClass = toClass;
		}

		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			return (T) SpringUtils.getUuidEntityObject(toClass, source.trim());
		}
	}
}

package coo.mvc.converter;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import coo.base.util.DateUtils;

/**
 * 自定义转换器配置管理。
 */
public class ConversionServiceFactoryBean extends
		FormattingConversionServiceFactoryBean {
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		getObject().removeConvertible(String.class, Enum.class);
		getObject().addConverterFactory(new StringToIEnum());
		getObject().addConverter(new IEnumToString());
		getObject().addConverter(new StringToParams());
		getObject().addConverter(new ParamsToString());
		getObject().addConverterFactory(new StringToUuidEntity());
		getObject().addConverter(new UuidEntityToString());

		getObject().addFormatter(new DateFormatter(DateUtils.TO_MILLISECOND));
		getObject().addFormatter(new DateFormatter(DateUtils.TO_SECOND));
		getObject().addFormatter(new DateFormatter(DateUtils.TO_MINUTE));
		getObject().addFormatter(new DateFormatter(DateUtils.TO_DATE));

	}
}

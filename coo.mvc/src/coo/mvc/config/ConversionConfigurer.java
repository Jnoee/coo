package coo.mvc.config;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;

import coo.base.util.DateUtils;
import coo.mvc.converter.IEnumToString;
import coo.mvc.converter.ParamsToString;
import coo.mvc.converter.StringToIEnum;
import coo.mvc.converter.StringToParams;
import coo.mvc.converter.StringToUuidEntity;
import coo.mvc.converter.UuidEntityToString;

/**
 * 转换器配置组件。
 */
@Component("coo.mvc.converter.ConversionConfigurer")
public class ConversionConfigurer extends AbstractConversionConfigurer {
  @Override
  public void config(FormattingConversionService conversionService) {
    conversionService.removeConvertible(String.class, Enum.class);
    conversionService.addConverterFactory(new StringToIEnum());
    conversionService.addConverter(new IEnumToString());
    conversionService.addConverter(new StringToParams());
    conversionService.addConverter(new ParamsToString());
    conversionService.addConverterFactory(new StringToUuidEntity());
    conversionService.addConverter(new UuidEntityToString());

    // 添加默认java.util.Date转换器
    conversionService.addFormatter(new DateFormatter(DateUtils.DAY));

    // 添加DateTimeFormat注解的日期时间转换器
    conversionService
        .addFormatterForFieldAnnotation(new DateTimeFormatAnnotationFormatterFactory());
    conversionService
        .addFormatterForFieldAnnotation(new JodaDateTimeFormatAnnotationFormatterFactory());
    conversionService
        .addFormatterForFieldAnnotation(new Jsr310DateTimeFormatAnnotationFormatterFactory());
  }
}

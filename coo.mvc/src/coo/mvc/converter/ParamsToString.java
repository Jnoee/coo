package coo.mvc.converter;

import org.springframework.core.convert.converter.Converter;

import coo.base.model.Params;

/**
 * Params转换成字符串转换器。
 */
public class ParamsToString implements Converter<Params, String> {
  @Override
  public String convert(Params source) {
    return source.toString();
  }
}

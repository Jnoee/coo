package coo.mvc.converter;

import org.springframework.core.convert.converter.Converter;

import coo.base.model.Params;

public class StringToParams implements Converter<String, Params> {
	@Override
	public Params convert(String source) {
		Params params = new Params();
		params.fromString(source);
		return params;
	}
}

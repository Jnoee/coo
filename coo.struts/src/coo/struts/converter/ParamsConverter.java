package coo.struts.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import coo.base.model.Params;

/**
 * 参数转换器。
 */
public class ParamsConverter extends StrutsTypeConverter {
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Params params = new Params();
		params.fromString(values[0]);
		return params;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		return o.toString();
	}
}

package coo.struts.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import coo.core.model.IEnum;
import coo.core.util.IEnumUtils;

/**
 * IEnum枚举类型转换器。
 */
public class IEnumConverter extends StrutsTypeConverter {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		return IEnumUtils.getIEnumByValue(toClass, values[0]);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		return ((IEnum) o).getValue();
	}
}

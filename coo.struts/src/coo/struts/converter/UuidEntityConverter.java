package coo.struts.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import coo.core.model.UuidEntity;
import coo.core.util.SpringUtils;

/**
 * UuidEntity业务实体转换器，实现UuidEntity的id与实体对象之间的自动转换。
 */
public class UuidEntityConverter extends StrutsTypeConverter {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		return SpringUtils.getUuidEntityObject(toClass, values[0]);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public String convertToString(Map context, Object o) {
		return ((UuidEntity) o).getId();
	}
}

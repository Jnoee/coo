package coo.core.hibernate.usertype;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.spi.SessionImplementor;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import coo.base.util.BeanUtils;
import coo.base.util.CollectionUtils;
import coo.core.jackson.GenericObjectMapper;

/**
 * Json格式自定义列表类型。
 */
public class JsonListUserType extends AbstractUserType {
	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };
	private ObjectMapper mapper = new GenericObjectMapper();

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner) throws SQLException {
		try {
			String value = getValue(rs, names[0], session);
			if (value == null) {
				value = "[]";
			}
			Field jsonField = getField(owner, names[0]);
			Class<?> beanClass = BeanUtils.getGenericFieldType(jsonField);
			JavaType type = mapper.getTypeFactory().constructCollectionType(
					List.class, beanClass);
			return mapper.readValue(value, type);
		} catch (Exception e) {
			throw new SQLException("转换Json为目标对象时发生异常。", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws SQLException {
		try {
			List<Object> values = (ArrayList<Object>) value;
			if (CollectionUtils.isNotEmpty(values)) {
				mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
				setValue(st, mapper.writeValueAsString(values), index, session);
			} else {
				setValue(st, null, index, session);
			}
		} catch (Exception e) {
			throw new SQLException("转换目标对象为Json时发生异常。", e);
		}
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return List.class;
	}
}

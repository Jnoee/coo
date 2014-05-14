package coo.core.hibernate.usertype;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.engine.spi.SessionImplementor;

import com.fasterxml.jackson.databind.ObjectMapper;

import coo.core.jackson.GenericObjectMapper;

/**
 * Json格式自定义类型。
 */
public class JsonUserType extends AbstractUserType {
	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };
	private ObjectMapper mapper = new GenericObjectMapper();

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner) throws SQLException {
		try {
			String value = getValue(rs, names[0], session);
			if (value != null) {
				Field jsonField = getField(rs, names[0], owner);
				Class<?> jsonClass = jsonField.getType();
				return mapper.readValue(value, jsonClass);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new SQLException("转换Json为目标对象时发生异常。", e);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws SQLException {
		try {
			if (value != null) {
				setValue(st, mapper.writeValueAsString(value), index, session);
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
		return Object.class;
	}
}

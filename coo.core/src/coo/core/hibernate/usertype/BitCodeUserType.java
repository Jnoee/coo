package coo.core.hibernate.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.engine.spi.SessionImplementor;

import coo.base.model.BitCode;

/**
 * BitCode自定义类型。
 */
public class BitCodeUserType extends AbstractUserType {
	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner) throws SQLException {
		String value = getValue(rs, names[0], session);
		if (value != null) {
			return new BitCode(value);
		} else {
			return null;
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws SQLException {
		if (value != null) {
			setValue(st, value.toString(), index, session);
		} else {
			setValue(st, null, index, session);
		}
	}

	@Override
	public Class<?> returnedClass() {
		return BitCode.class;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}
}

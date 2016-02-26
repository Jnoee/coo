package coo.core.hibernate.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.joda.time.LocalTime;

/**
 * LocalTime自定义类型。
 */
public class LocalTimeUserType extends AbstractUserType {
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		String value = getValue(rs, names[0], session);
		if (value != null) {
			return new LocalTime(value);
		} else {
			return null;
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		if (value != null) {
			LocalTime localTime = (LocalTime) value;
			setValue(st, localTime.toString("HH:mm:ss"), index, session);
		} else {
			setValue(st, null, index, session);
		}
	}

	@Override
	public Class<?> returnedClass() {
		return LocalTime.class;
	}
}

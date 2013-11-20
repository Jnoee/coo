package coo.core.hibernate.usertype;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import coo.core.model.IEnum;
import coo.core.util.IEnumUtils;

/**
 * 用于Hibernate的自定义类型，映射实现了IEnum接口的枚举类型。
 */
public class IEnumUserType extends AbstractUserType {
	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };

	@SuppressWarnings("unchecked")
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner) throws SQLException {
		try {
			String value = getValue(rs, names[0], session);
			if (value != null) {
				Field enumField = getField(owner, names[0]);
				Class<? extends IEnum> enumClass = (Class<? extends IEnum>) enumField
						.getType();
				return IEnumUtils.getIEnumByValue(enumClass, value);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new HibernateException("转换IEnum枚举类型时发生异常。", e);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws SQLException {
		try {
			if (value != null) {
				setValue(st, ((IEnum) value).getValue(), index, session);
			} else {
				setValue(st, null, index, session);
			}
		} catch (Exception e) {
			throw new HibernateException("转换IEnum枚举类型时发生异常。", e);
		}
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return IEnum.class;
	}
}

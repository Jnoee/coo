package coo.core.hibernate.usertype;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

import coo.base.util.StringUtils;
import coo.core.model.UuidEntity;

/**
 * UuidEntity自定义类型。
 */
public class UuidEntityUserType extends AbstractUserType {
	@Override
	@SuppressWarnings("unchecked")
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner) throws SQLException {
		try {
			String value = getValue(rs, names[0], session);

			if (StringUtils.isNotBlank(value)) {
				Field uuidEntityField = getField(rs, names[0], owner);
				Class<? extends UuidEntity> uuidEntityClass = (Class<? extends UuidEntity>) uuidEntityField
						.getType();
				return ((Session) session).get(uuidEntityClass, value);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new HibernateException("转换UuidEntity类型时发生异常。", e);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws SQLException {
		try {
			if (value != null) {
				UuidEntity entity = (UuidEntity) value;
				setValue(st, entity.getId(), index, session);
			} else {
				setValue(st, null, index, session);
			}
		} catch (Exception e) {
			throw new HibernateException("转换UuidEntity类型时发生异常。", e);
		}
	}

	@Override
	public Class<?> returnedClass() {
		return UuidEntity.class;
	}
}

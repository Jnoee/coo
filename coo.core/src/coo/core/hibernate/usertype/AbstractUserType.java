package coo.core.hibernate.usertype;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;

/**
 * 用户自定义类型抽象基类。
 */
public abstract class AbstractUserType implements UserType {
	/**
	 * 根据字段标签名获取源对象中对应字段的属性对象。
	 * 
	 * @param rs
	 *            数据结果集
	 * @param columnLabel
	 *            Hibernate生成SQL中的字段标签
	 * @param owner
	 *            源对象
	 * @return 返回源对象中对应字段的属性对象。
	 */
	protected Field getField(ResultSet rs, String columnLabel, Object owner) {
		try {
			String columnName = rs.getMetaData().getColumnName(
					rs.findColumn(columnLabel));
			// 如果使用了@Column注解，优先用@Column注解设定的名称进行匹配
			for (Field field : BeanUtils.findField(owner.getClass(),
					Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if (StringUtils.isNotBlank(column.name())
						&& column.name().equalsIgnoreCase(columnName)) {
					return field;
				}
			}
			// 如果从@Column注解没有找到则从全部属性中进行匹配
			for (Field field : BeanUtils.getAllDeclaredField(owner.getClass())) {
				if (field.isAnnotationPresent(Type.class)
						&& field.getName().equalsIgnoreCase(columnName)) {
					return field;
				}
			}
			throw new UncheckedException("没有找到" + owner.getClass() + "中对应"
					+ columnName + "字段的属性。");
		} catch (Exception e) {
			throw new UncheckedException("获取字段名时发生异常。", e);
		}
	}

	/**
	 * 获取指定字段的值。
	 * 
	 * @param rs
	 *            ResultSet
	 * @param name
	 *            字段名
	 * @param session
	 *            SessionImplementor
	 * @return 返回指定字段的值。
	 * @throws HibernateException
	 *             当发生Hibernate操作异常时抛出
	 * @throws SQLException
	 *             当发生SQL异常时抛出
	 */
	protected String getValue(ResultSet rs, String name,
			SessionImplementor session) throws SQLException {
		return (String) StringType.INSTANCE.get(rs, name, session);
	}

	/**
	 * 设置指定字段的值。
	 * 
	 * @param st
	 *            PreparedStatement
	 * @param value
	 *            字段值
	 * @param index
	 *            字段序号
	 * @param session
	 *            SessionImplementor
	 * @throws HibernateException
	 *             当发生Hibernate操作异常时抛出
	 * @throws SQLException
	 *             当发生SQL异常时抛出
	 */
	protected void setValue(PreparedStatement st, String value, int index,
			SessionImplementor session) throws SQLException {
		StringType.INSTANCE.set(st, value, index, session);
	}

	@Override
	public boolean equals(Object x, Object y) {
		if (x != null) {
			return x.equals(y);
		}
		return false;
	}

	@Override
	public int hashCode(Object x) {
		return x.hashCode();
	}

	@Override
	public Object deepCopy(Object value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) {
		return original;
	}
}

package coo.core.hibernate.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.engine.spi.SessionImplementor;

import coo.base.util.CollectionUtils;
import coo.base.util.StringUtils;

/**
 * 字符串列表自定义类型。
 */
public class ArrayListUserType extends AbstractListUserType {
  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
      throws SQLException {
    String value = getValue(rs, names[0], session);
    if (value != null) {
      return Arrays.asList(value.split(","));
    } else {
      return new String[] {};
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
      throws SQLException {
    @SuppressWarnings("unchecked")
    List<String> values = (List<String>) value;
    if (CollectionUtils.isNotEmpty(values)) {
      setValue(st, StringUtils.join(values, ","), index, session);
    } else {
      setValue(st, null, index, session);
    }
  }
}

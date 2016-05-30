package coo.core.hibernate.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.engine.spi.SessionImplementor;

import coo.base.model.Params;

/**
 * 参数自定义类型。
 */
public class ParamsUserType extends AbstractUserType {
  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
      throws SQLException {
    String value = getValue(rs, names[0], session);
    if (value != null) {
      Params params = new Params();
      params.fromString(rs.getString(names[0]));
      return params;
    } else {
      return new Params();
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
      throws SQLException {
    if (value != null && !((Params) value).isEmpty()) {
      setValue(st, value.toString(), index, session);
    } else {
      setValue(st, null, index, session);
    }
  }

  @Override
  public Class<?> returnedClass() {
    return Params.class;
  }
}

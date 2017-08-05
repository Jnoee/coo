package coo.core.hibernate.usertype;

import java.util.List;

/**
 * 用户自定义列表类型抽象基类。
 */
public abstract class AbstractListUserType extends AbstractUserType {
  @Override
  public Class<?> returnedClass() {
    return List.class;
  }
}

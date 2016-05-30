package coo.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * IEnum枚举模块。
 */
public class IEnumModule extends SimpleModule {
  private static final long serialVersionUID = 4364404372582339545L;

  /**
   * 构造方法。
   */
  public IEnumModule() {
    super("jackson-datatype-ienum");
    addSerializer(new IEnumSerializer());
  }
}

package coo.mvc.converter;

import org.springframework.format.support.FormattingConversionService;

/**
 * 转换器设置抽象类。
 */
public abstract class AbstractConverterSettings
    implements Comparable<AbstractConverterSettings> {
  /** 加载序号 */
  private Integer order = 0;

  /**
   * 设置转换器。
   * 
   * @param conversionService 转换器服务组件
   */
  public abstract void config(FormattingConversionService conversionService);

  @Override
  public int compareTo(AbstractConverterSettings other) {
    return other.getOrder() - order;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }
}

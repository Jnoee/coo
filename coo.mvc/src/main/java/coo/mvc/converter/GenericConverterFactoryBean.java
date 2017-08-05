package coo.mvc.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * 自定义转换器配置管理。
 */
public class GenericConverterFactoryBean extends FormattingConversionServiceFactoryBean
    implements ApplicationContextAware {
  private List<AbstractConverterSettings> settings = new ArrayList<>();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    Map<String, AbstractConverterSettings> configurersMap =
        applicationContext.getBeansOfType(AbstractConverterSettings.class);
    settings.addAll(configurersMap.values());
    Collections.sort(settings);
  }

  @Override
  public void afterPropertiesSet() {
    super.afterPropertiesSet();
    for (AbstractConverterSettings setting : settings) {
      setting.config(getObject());
    }
  }
}

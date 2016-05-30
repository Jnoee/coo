package coo.mvc.config;

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
public class ConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean
    implements ApplicationContextAware {
  private List<AbstractConversionConfigurer> configurers =
      new ArrayList<AbstractConversionConfigurer>();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    Map<String, AbstractConversionConfigurer> configurersMap =
        applicationContext.getBeansOfType(AbstractConversionConfigurer.class);
    configurers.addAll(configurersMap.values());
    Collections.sort(configurers);
  }

  @Override
  public void afterPropertiesSet() {
    super.afterPropertiesSet();
    for (AbstractConversionConfigurer configurer : configurers) {
      configurer.config(getObject());
    }
  }
}

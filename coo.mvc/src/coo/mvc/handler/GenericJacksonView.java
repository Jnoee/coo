package coo.mvc.handler;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import coo.core.jackson.GenericObjectMapper;
import coo.core.jackson.IEnumModule;

/**
 * 自定义基于Jackson2的json视图。
 */
public class GenericJacksonView extends MappingJackson2JsonView {
  /**
   * 构造方法。
   */
  public GenericJacksonView() {
    GenericObjectMapper mapper = new GenericObjectMapper();
    // 注册IEnum枚举模块
    mapper.registerModule(new IEnumModule());
    setObjectMapper(mapper);
    setExtractValueFromSingleKeyModel(true);
  }
}

package coo.mvc.handler;

import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import coo.core.jackson.GenericXmlMapper;
import coo.core.jackson.IEnumModule;

public class GenericJackson2XmlView extends MappingJackson2XmlView {
  /**
   * 构造方法。
   */
  public GenericJackson2XmlView() {
    GenericXmlMapper mapper = new GenericXmlMapper();
    // 注册IEnum枚举模块
    mapper.registerModule(new IEnumModule());
    setObjectMapper(mapper);
  }
}

package coo.mvc.handler;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import coo.core.jackson.GenericObjectMapper;

/**
 * 自定义基于Jackson2的json视图。
 */
public class GenericJacksonView extends MappingJackson2JsonView {
	/**
	 * 构造方法。
	 */
	public GenericJacksonView() {
		setObjectMapper(new GenericObjectMapper().registerEnumModule());
		setExtractValueFromSingleKeyModel(true);
	}
}

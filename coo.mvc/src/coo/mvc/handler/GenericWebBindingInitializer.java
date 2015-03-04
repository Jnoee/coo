package coo.mvc.handler;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 常规数据绑定初始化器。
 */
public class GenericWebBindingInitializer extends
		ConfigurableWebBindingInitializer {
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		super.initBinder(binder, request);
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}
}
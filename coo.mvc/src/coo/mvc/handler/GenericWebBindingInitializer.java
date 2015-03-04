package coo.mvc.handler;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 常规数据绑定初始化器。
 */
public class GenericWebBindingInitializer implements WebBindingInitializer {
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}
}
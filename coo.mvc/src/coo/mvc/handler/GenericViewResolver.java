package coo.mvc.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * 多视图处理。
 */
public class GenericViewResolver extends ContentNegotiatingViewResolver {
	public GenericViewResolver() {
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.setDefaultContentType(MediaType.TEXT_HTML);
		contentNegotiationManager.setIgnoreAcceptHeader(true);
		contentNegotiationManager.addMediaType("json",
				MediaType.APPLICATION_JSON);
		contentNegotiationManager
				.addMediaType("xml", MediaType.APPLICATION_XML);
		setContentNegotiationManager(contentNegotiationManager.getObject());

		List<View> defaultViews = new ArrayList<View>();
		defaultViews.add(new GenericJacksonView());
		defaultViews.add(new GenericXStreamView());
		setDefaultViews(defaultViews);
	}
}

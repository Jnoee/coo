package coo.mvc.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义WebMvcConfigurerAdapter。
 */
public class GenericWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
    Map<String, MediaType> mediaTypes = new HashMap<>();
    mediaTypes.put("json", MediaType.APPLICATION_JSON);
    mediaTypes.put("xml", MediaType.APPLICATION_XML);
    configurer.mediaTypes(mediaTypes);
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.enableContentNegotiation(new GenericJackson2JsonView(), new GenericJackson2XmlView());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/base/**").addResourceLocations("classpath:/coo/mvc/static/base/");
    registry.addResourceHandler("/dwz/**").addResourceLocations("classpath:/coo/mvc/static/dwz/");
    registry.addResourceHandler("/ecs/**").addResourceLocations("classpath:/coo/mvc/static/ecs/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/index");

    registry.addViewController("/500").setViewName("/500");
    registry.addViewController("/404").setViewName("/404");
    registry.addViewController("/403").setViewName("/403");
  }
}

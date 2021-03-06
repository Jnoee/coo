package coo.mvc.config;


import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import coo.base.constants.Encoding;
import coo.core.config.FreeMarkerProperties;
import coo.core.message.MessageSource;
import coo.mvc.converter.GenericConverterFactoryBean;
import coo.mvc.freemarker.GenericFreeMarkerConfigurer;
import coo.mvc.handler.DwzErrorController;
import coo.mvc.handler.GenericContainerCustomizer;
import coo.mvc.handler.GenericWebBindingInitializer;
import coo.mvc.handler.GenericWebMvcConfigurerAdapter;

/**
 * 组件配置。
 */
@Configuration
@ComponentScan("coo.mvc")
public class MvcConfiguration {
  /**
   * 配置FreeMarker模版组件。
   * 
   * @param freemarkerProperties FreeMarker配置属性
   * @return 返回FreeMarker模版组件。
   */
  @Bean
  public FreeMarkerConfigurer freemarkerConfigurer(FreeMarkerProperties freemarkerProperties) {
    GenericFreeMarkerConfigurer configurer = new GenericFreeMarkerConfigurer();
    configurer.setFreemarkerSettings(freemarkerProperties.toProperties());
    return configurer;
  }

  /**
   * 配置文件上传组件。
   * 
   * @return 返回文件上传组件。
   */
  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setDefaultEncoding(Encoding.UTF_8);
    return resolver;
  }

  /**
   * 配置异常处理组件。
   * 
   * @param errorAttributes ErrorAttributes组件
   * @return 返回异常处理组件。
   */
  @Bean
  @ConditionalOnMissingBean(ErrorController.class)
  public ErrorController errorController(ErrorAttributes errorAttributes) {
    return new DwzErrorController(errorAttributes, new ErrorProperties());
  }

  /**
   * 配置验证组件。
   * 
   * @param messageSource MessageSource组件
   * @return 返回验证组件。
   */
  @Bean
  public Validator validator(MessageSource messageSource) {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setProviderClass(HibernateValidator.class);
    validator.setValidationMessageSource(messageSource);
    return validator;
  }

  /**
   * 配置转换组件。
   * 
   * @return 返回转换组件。
   */
  @Bean
  public GenericConverterFactoryBean conversionService() {
    return new GenericConverterFactoryBean();
  }

  /**
   * 配置ConfigurableWebBindingInitializer组件。
   * 
   * @param validator 验证组件
   * @return 返回ConfigurableWebBindingInitializer组件。
   */
  @Bean
  public ConfigurableWebBindingInitializer webBindingInitializer(Validator validator) {
    GenericWebBindingInitializer initializer = new GenericWebBindingInitializer();
    initializer.setConversionService(conversionService().getObject());
    initializer.setValidator(validator);
    return initializer;
  }

  /**
   * 配置WebMvcConfigurerAdapter组件。
   * 
   * @return 返回WebMvcConfigurerAdapter组件。
   */
  @Bean
  public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
    return new GenericWebMvcConfigurerAdapter();
  }

  /**
   * 配置EmbeddedServletContainerCustomizer组件。
   * 
   * @return 返回EmbeddedServletContainerCustomizer组件。
   */
  @Bean
  public EmbeddedServletContainerCustomizer contianerCustomizer() {
    return new GenericContainerCustomizer();
  }
}

package coo.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import coo.core.mail.MailSender;

/**
 * 组件配置。
 */
@Configuration
@EnableConfigurationProperties({FreeMarkerProperties.class, MailProperties.class})
public class SupportConfiguration {
  /**
   * 邮件模版配置。
   * 
   * @param freemarkerProperties 模版配置属性
   * @return 返回邮件模版配置。
   */
  @Bean
  public FreeMarkerConfigurationFactoryBean mailTemplateConfigurer(
      FreeMarkerProperties freemarkerProperties) {
    FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
    factoryBean.setTemplateLoaderPath("classpath:/META-INF/coo/mail");
    factoryBean.setFreemarkerSettings(freemarkerProperties.toProperties());
    return factoryBean;
  }

  /**
   * 配置邮件发送组件。
   * 
   * @return 返回邮件发送组件。
   */
  @Bean
  public MailSender mailSender() {
    return new MailSender(new MailProperties());
  }
}

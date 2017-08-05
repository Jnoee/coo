package coo.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import coo.core.mail.MailSender;

@Configuration
@EnableConfigurationProperties({FreeMarkerProperties.class, MailProperties.class})
public class SupportConfiguration {
  @Bean
  public FreeMarkerConfigurationFactoryBean mailTemplateConfigurer(
      FreeMarkerProperties freemarkerProperties) {
    FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
    factoryBean.setTemplateLoaderPath("classpath:/META-INF/coo/mail");
    factoryBean.setFreemarkerSettings(freemarkerProperties.toProperties());
    return factoryBean;
  }

  @Bean
  public MailSender mailSender() {
    return new MailSender(new MailProperties());
  }
}

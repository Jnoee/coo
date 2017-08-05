package coo.mvc.security.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import coo.mvc.security.handler.DwzSecurityErrorController;
import coo.mvc.security.interceptor.AuthInterceptor;
import coo.mvc.security.permission.PermissionConfig;
import coo.mvc.security.service.LoginRealm;

/**
 * 组件配置。
 */
@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
public class SecurityConfiguration extends WebMvcConfigurerAdapter {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 权限注解拦截器
    registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
  }

  /**
   * 配置LifecycleBeanPostProcessor组件。
   * 
   * @return 返回LifecycleBeanPostProcessor组件。
   */
  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /**
   * 配置RealmCacheManager组件。
   * 
   * @return 返回RealmCacheManager组件。
   */
  @Bean
  public CacheManager realmCacheManager() {
    return new MemoryConstrainedCacheManager();
  }

  /**
   * 配置LoginRealm组件。
   * 
   * @return 返回LoginRealm组件。
   */
  @Bean
  @DependsOn("daoRegister")
  public LoginRealm loginRealm() {
    LoginRealm realm = new LoginRealm();
    realm.setCacheManager(realmCacheManager());
    return realm;
  }

  /**
   * 配置SecurityManager组件。
   * 
   * @return 返回SecurityManager组件。
   */
  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(loginRealm());
    return securityManager;
  }

  /**
   * 配置ShiroFilter组件。
   * 
   * @return 返回ShiroFilter组件。
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilter() {
    ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
    factory.setSecurityManager(securityManager());
    factory.setLoginUrl("/login");
    factory.setUnauthorizedUrl("/login");
    factory.setSuccessUrl("/");
    return factory;
  }

  /**
   * 配置权限管理组件。
   * 
   * @return 返回权限管理组件。
   */
  @Bean
  public PermissionConfig permissionConfig() {
    PermissionConfig config = new PermissionConfig();
    config.init();
    return config;
  }

  /**
   * 配置异常处理组件。
   * 
   * @param errorAttributes ErrorAttributes组件
   * @return 返回异常处理组件。
   */
  @Bean
  public ErrorController errorController(ErrorAttributes errorAttributes) {
    return new DwzSecurityErrorController(errorAttributes, new ErrorProperties());
  }
}

package coo.mvc.security.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import coo.mvc.security.entity.ResourceEntity;

/**
 * ResourceEntity切面。
 */
@Aspect
@Configuration
public class ResourceEntityAspect {
  /**
   * 切面处理方法。
   * 
   * @param joinPoint 切入点
   */
  @Before("@annotation(coo.mvc.security.annotations.AutoFillIn)")
  public void before(JoinPoint joinPoint) {
    Object[] params = joinPoint.getArgs();
    for (Object param : params) {
      if (param instanceof ResourceEntity<?>) {
        ((ResourceEntity<?>) param).autoFillIn();
      }
    }
  }
}

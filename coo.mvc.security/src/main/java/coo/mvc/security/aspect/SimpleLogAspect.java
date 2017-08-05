package coo.mvc.security.aspect;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import coo.base.util.StringUtils;
import coo.core.util.AspectUtils;
import coo.mvc.security.annotations.SimpleLog;
import coo.mvc.security.entity.BnLogEntity;

/**
 * 简单业务日志切面。
 */
@Aspect
@Configuration
public class SimpleLogAspect extends AbstractLogAspect {
  /**
   * 切面处理方法。
   * 
   * @param joinPoint 切入点
   * @throws Throwable 切面处理失败时抛出异常。
   * @return 返回方法本身返回的对象。
   */
  @Around("@annotation(coo.mvc.security.annotations.SimpleLog)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    SimpleLog simpleLog = AspectUtils.getAnnotation(joinPoint, SimpleLog.class);
    Map<String, Object> params = AspectUtils.getMethodParams(joinPoint);
    BnLogEntity bnLog = newBnLog();
    bnLog.setEntityId(getEntityId(simpleLog.entityId(), params));
    bnLog.setMessage(getMessage(simpleLog.code(), simpleLog.vars(), params));
    Object result = joinPoint.proceed();
    saveBnLog(bnLog);
    return result;
  }

  /**
   * 获取业务实体ID。
   * 
   * @param entityId 业务实体ID名称
   * @param params 方法参数
   * @return 返回业务实体ID。
   */
  private String getEntityId(String entityId, Map<String, Object> params) {
    if (StringUtils.isBlank(entityId)) {
      return null;
    }
    Object entityField = AspectUtils.getParam(entityId, params);
    if (entityField != null) {
      return entityField.toString();
    }
    return null;
  }
}

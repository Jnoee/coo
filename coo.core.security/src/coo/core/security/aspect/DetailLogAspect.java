package coo.core.security.aspect;

import java.lang.reflect.Field;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import coo.base.util.Assert;
import coo.base.util.BeanUtils;
import coo.base.util.StringUtils;
import coo.core.hibernate.dao.DaoUtils;
import coo.core.security.annotations.DetailLog;
import coo.core.security.entity.BnLogEntity;
import coo.core.util.AspectUtils;

/**
 * 详细业务日志切面。
 */
@Aspect
public class DetailLogAspect extends AbstractLogAspect {
  /**
   * 切面处理方法。
   * 
   * @param joinPoint 切入点
   * @throws Throwable 切面处理失败时抛出异常。
   * @return 返回方法本身返回的对象。
   */
  @Around("@annotation(coo.core.security.annotations.DetailLog)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    DetailLog log = AspectUtils.getAnnotation(joinPoint, DetailLog.class);
    Map<String, Object> params = AspectUtils.getMethodParams(joinPoint);

    Object target = getEntity(params.get(log.target()));
    Assert.notNull(target);

    BnLogEntity bnLog = newBnLog();
    bnLog.setMessage(getMessage(log.code(), log.vars(), params));

    Object result = null;
    switch (log.type()) {
      case ALL:
        result = processAll(bnLog, target, joinPoint);
        break;
      case ORIG:
        result = processOrig(bnLog, target, joinPoint);
        break;
      case NEW:
        result = processNew(bnLog, target, joinPoint);
        break;
      default:
        break;
    }

    saveBnLog(bnLog);
    return result;
  }

  /**
   * 记录原记录和新记录。
   * 
   * @param bnLog 业务日志
   * @param target 目标对象
   * @param joinPoint 切入点
   * @throws Throwable 切面处理失败时抛出异常。
   * @return 返回方法本身返回的对象。
   */
  private Object processAll(BnLogEntity bnLog, Object target, ProceedingJoinPoint joinPoint)
      throws Throwable {
    bnLog.setEntityId(getEntityId(target));
    bnLog.setOrigData(target);
    Object result = joinPoint.proceed();
    target = getEntity(target);
    bnLog.setNewData(target);
    return result;
  }

  /**
   * 记录原记录。
   * 
   * @param bnLog 业务日志
   * @param target 目标对象
   * @param joinPoint 切入点
   * @throws Throwable 切面处理失败时抛出异常。
   * @return 返回方法本身返回的对象。
   */
  private Object processOrig(BnLogEntity bnLog, Object target, ProceedingJoinPoint joinPoint)
      throws Throwable {
    bnLog.setEntityId(getEntityId(target));
    bnLog.setOrigData(target);
    return joinPoint.proceed();
  }

  /**
   * 记录新记录。
   * 
   * @param bnLog 业务日志
   * @param target 目标对象
   * @param joinPoint 切入点
   * @throws Throwable 切面处理失败时抛出异常。.
   * @return 返回方法本身返回的对象。
   */
  private Object processNew(BnLogEntity bnLog, Object target, ProceedingJoinPoint joinPoint)
      throws Throwable {
    Object result = joinPoint.proceed();
    bnLog.setEntityId(getEntityId(target));
    bnLog.setNewData(target);
    return result;
  }

  /**
   * 获取业务实体。
   * 
   * @param target 日志目标对象
   * @return 如果目标对象是UuidEntity返回对应的业务实体，否则返回原目标对象。
   */
  private Object getEntity(Object target) {
    String entityId = getEntityId(target);
    if (StringUtils.isNotBlank(entityId)) {
      return DaoUtils.getEntity(target.getClass(), entityId);
    }
    return target;
  }

  /**
   * 获取业务实体ID。
   * 
   * @param target 日志目标对象
   * @return 返回业务实体ID。
   */
  private String getEntityId(Object target) {
    Field idField = BeanUtils.findField(target.getClass(), "id");
    if (idField != null) {
      Object idValue = BeanUtils.getField(target, idField);
      return idValue != null ? idValue.toString() : null;
    } else {
      return null;
    }
  }
}

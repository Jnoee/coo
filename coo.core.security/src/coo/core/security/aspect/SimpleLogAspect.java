package coo.core.security.aspect;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import coo.base.util.BeanUtils;
import coo.base.util.StringUtils;
import coo.core.security.annotations.SimpleLog;
import coo.core.security.entity.BnLogEntity;
import coo.core.util.AspectUtils;

/**
 * 简单业务日志切面。
 */
@Aspect
public class SimpleLogAspect extends AbstractLogAspect {
	/**
	 * 切面处理方法。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @throws Throwable
	 *             切面处理失败时抛出异常。
	 */
	@Around("@annotation(coo.core.security.annotations.SimpleLog)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		SimpleLog simpleLog = AspectUtils.getAnnotation(joinPoint,
				SimpleLog.class);
		Map<String, Object> params = AspectUtils.getMethodParams(joinPoint);
		BnLogEntity bnLog = newBnLog();
		bnLog.setEntityId(getEntityId(simpleLog.entityId(), params));
		bnLog.setMessage(getMessage(simpleLog.code(), simpleLog.vars(), params));
		joinPoint.proceed();
		saveBnLog(bnLog);
	}

	/**
	 * 获取业务实体ID。
	 * 
	 * @param entityId
	 *            业务实体ID名称
	 * @param params
	 *            方法参数
	 * @return 返回业务实体ID。
	 */
	private String getEntityId(String entityId, Map<String, Object> params) {
		if (StringUtils.isBlank(entityId)) {
			return null;
		}
		Object entityField = null;
		if (entityId.contains(".")) {
			String targetName = StringUtils.substringBefore(entityId, ".");
			entityId = StringUtils.substringAfter(entityId, ".");
			entityField = BeanUtils.getField(params.get(targetName), entityId);
		} else {
			entityField = params.get(entityId);
		}
		if (entityField != null) {
			return entityField.toString();
		}
		return null;
	}
}
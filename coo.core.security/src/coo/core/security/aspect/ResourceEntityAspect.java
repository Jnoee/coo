package coo.core.security.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import coo.core.security.entity.ResourceEntity;

/**
 * ResourceEntity切面。
 */
@Aspect
public class ResourceEntityAspect {
	/**
	 * 切面处理方法。
	 * 
	 * @param joinPoint
	 *            切入点
	 */
	@Before("@annotation(coo.core.security.annotations.AutoFillIn)")
	public void before(JoinPoint joinPoint) {
		Object[] params = joinPoint.getArgs();
		for (Object param : params) {
			if (param instanceof ResourceEntity<?>) {
				((ResourceEntity<?>) param).autoFillIn();
			}
		}
	}
}
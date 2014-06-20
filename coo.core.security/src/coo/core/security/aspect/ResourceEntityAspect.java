package coo.core.security.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import coo.core.security.annotations.AutoFillIn;
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
		AutoFillIn autoFill = getAutoFill(joinPoint);
		Object[] params = joinPoint.getArgs();
		for (Object param : params) {
			if (param instanceof ResourceEntity<?>) {
				if (autoFill.admin()) {
					((ResourceEntity<?>) param).autoFillInByAdmin();
				} else {
					((ResourceEntity<?>) param).autoFillIn();
				}
			}
		}
	}

	/**
	 * 获取切入方法上的注解。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @return 返回切入方法上的注解。
	 */
	private AutoFillIn getAutoFill(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		return methodSignature.getMethod().getAnnotation(AutoFillIn.class);
	}
}
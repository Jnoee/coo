package coo.core.util;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 切面工具类。
 */
public class AspectUtils {
	/**
	 * 从切入点获取方法的参数。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @return 返回切入点方法的参数。
	 */
	public static Map<String, Object> getMethodParams(
			ProceedingJoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		Object[] paramValues = joinPoint.getArgs();
		String[] paramNames = methodSignature.getParameterNames();
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < paramValues.length; i++) {
			params.put(paramNames[i], paramValues[i]);
		}
		return params;
	}

	/**
	 * 获取切入方法上指定的注解。
	 * 
	 * @param <T>
	 *            注解类型
	 * @param joinPoint
	 *            切入点
	 * @param annotationClass
	 *            注解类型
	 * @return 返回切入方法上指定的注解。
	 */
	public static <T extends Annotation> T getAnnotation(JoinPoint joinPoint,
			Class<T> annotationClass) {
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		return methodSignature.getMethod().getAnnotation(annotationClass);
	}
}
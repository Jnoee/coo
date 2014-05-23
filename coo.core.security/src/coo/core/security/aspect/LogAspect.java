package coo.core.security.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import coo.base.util.BeanUtils;
import coo.base.util.StringUtils;
import coo.core.hibernate.dao.DaoUtils;
import coo.core.message.MessageSource;
import coo.core.model.UuidEntity;
import coo.core.security.annotations.Log;
import coo.core.security.entity.BnLogEntity;
import coo.core.security.service.AbstractBnLogger;
import coo.core.util.SpringUtils;

/**
 * 日志切面。
 */
@Aspect
public class LogAspect {
	/**
	 * 环绕填充业务日志。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @throws Throwable
	 *             切入方法执行失败时抛出异常。
	 */
	@Around("@annotation(coo.core.security.annotations.Log)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		Log log = getLogs(joinPoint);
		Object target = joinPoint.getArgs()[log.target()];
		if (target instanceof UuidEntity) {
			processEntity(joinPoint);
		} else {
			processBean(joinPoint);
		}
	}

	/**
	 * 处理业务实体类型的日志。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @throws Throwable
	 *             切入方法执行失败时抛出异常。
	 */
	private void processEntity(ProceedingJoinPoint joinPoint) throws Throwable {
		Log log = getLogs(joinPoint);
		UuidEntity entity = getEntity(joinPoint.getArgs()[log.target()]);

		AbstractBnLogger<? extends BnLogEntity> bnLogger = SpringUtils
				.getBean("bnLogger");
		BnLogEntity bnLog = bnLogger.newBnLog();
		bnLog.setMessage(getMessage(entity, log));

		switch (log.type()) {
		case ALL:
			bnLog.setOrigData(entity);
			joinPoint.proceed();
			entity = getEntity(entity);
			bnLog.setNewData(entity);
			break;
		case ORIG:
			bnLog.setOrigData(entity);
			joinPoint.proceed();
			break;
		case NEW:
			joinPoint.proceed();
			bnLog.setNewData(entity);
			break;
		case NONE:
			joinPoint.proceed();
			break;
		}
		bnLogger.log(bnLog);
	}

	/**
	 * 处理一般对象类型的日志。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @throws Throwable
	 *             切入方法执行失败时抛出异常。
	 */
	private void processBean(ProceedingJoinPoint joinPoint) throws Throwable {
		Log log = getLogs(joinPoint);
		Object bean = joinPoint.getArgs()[log.target()];

		AbstractBnLogger<? extends BnLogEntity> bnLogger = SpringUtils
				.getBean("bnLogger");
		BnLogEntity bnLog = bnLogger.newBnLog();
		bnLog.setMessage(getMessage(bean, log));

		switch (log.type()) {
		case ALL:
			bnLog.setOrigData(bean);
			joinPoint.proceed();
			bnLog.setNewData(bean);
			break;
		case ORIG:
			bnLog.setOrigData(bean);
			joinPoint.proceed();
			break;
		case NEW:
			joinPoint.proceed();
			bnLog.setNewData(bean);
			break;
		case NONE:
			joinPoint.proceed();
			break;
		}
		bnLogger.log(bnLog);
	}

	/**
	 * 获取切入方法上的注解。
	 * 
	 * @param joinPoint
	 *            切入点
	 * @return 返回切入方法上的注解。
	 */
	private Log getLogs(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		return methodSignature.getMethod().getAnnotation(Log.class);
	}

	/**
	 * 获取业务实体。
	 * 
	 * @param target
	 *            日志目标对象
	 * @return 返回业务实体。
	 */
	private UuidEntity getEntity(Object target) {
		UuidEntity entity = (UuidEntity) target;
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = DaoUtils.getUuidEntity(entity.getClass(), entity.getId());
		}
		return entity;
	}

	/**
	 * 获取日志信息。
	 * 
	 * @param target
	 *            日志目标对象
	 * @param log
	 *            日志注解
	 * @return 返回日志信息。
	 */
	private String getMessage(Object target, Log log) {
		List<Object> vars = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(log.vars())) {
			for (String fieldName : log.vars().split(",")) {
				vars.add(BeanUtils.getField(target, fieldName));
			}
		}
		MessageSource messageSource = SpringUtils.getBean("messageSource");
		return messageSource.get(log.code(), vars.toArray());
	}
}

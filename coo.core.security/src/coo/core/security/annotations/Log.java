package coo.core.security.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	/**
	 * 日志记录目标对象位于方法参数中的位置，默认第1个参数，下标为0。
	 */
	int target() default 0;

	/**
	 * 日志信息编码，对应资源文件中定义的日志信息。
	 */
	String code() default "";

	/**
	 * 日志信息变量，对应日志信息中的变量，指定为目标对象的属性名。
	 */
	String vars() default "";

	/**
	 * 高级日志类型，默认不记录高级日志。
	 */
	LogType type() default LogType.NONE;

	/**
	 * 日志类型。
	 */
	public enum LogType {
		/** 不记录高级日志 */
		NONE,
		/** 记录原值和新值 */
		ALL,
		/** 记录原值 */
		ORIG,
		/** 记录新值 */
		NEW;
	}
}

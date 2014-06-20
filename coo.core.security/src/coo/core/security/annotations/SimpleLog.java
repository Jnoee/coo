package coo.core.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 简单业务日志注解。
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SimpleLog {
	/** 日志信息编码，对应资源文件中定义的日志信息。 */
	String code();

	/**
	 * 日志信息变量，对应日志信息中的变量，指定为参数对象名或参数对象的属性名。
	 */
	String[] vars() default "";

	/** 业务实体ID */
	String entityId() default "";
}
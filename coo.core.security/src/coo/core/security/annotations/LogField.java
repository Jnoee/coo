package coo.core.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import coo.base.util.DateUtils;

/**
 * 高级日志字段注解。
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogField {
	/**
	 * 日志记录的属性名称。
	 * 
	 * @return 返回日志记录的属性名称。
	 */
	String text();

	/**
	 * 当属性是关联对象时，指定记录关联对象哪个属性。
	 * 
	 * @return 返回关联属性。
	 */
	String property() default "";

	/**
	 * 当属性是日期类型时，指定记录的日期格式。
	 * 
	 * @return 返回日期格式。
	 */
	String format() default DateUtils.SECOND;
}
package coo.core.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 详细业务日志注解。
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DetailLog {
  /**
   * 日志记录目标对象在方法参数中的名称。
   * 
   * @return 返回日志记录目标对象在方法参数中的名称。
   */
  String target();

  /**
   * 日志类型。
   * 
   * @return 返回日志类型。
   */
  LogType type();

  /**
   * 日志信息编码，对应资源文件中定义的日志信息。
   * 
   * @return 返回日志信息编码。
   */
  String code();

  /**
   * 日志信息变量，对应日志信息中的变量，指定为目标对象的属性名。
   * 
   * @return 返回日志信息变量。
   */
  String[] vars() default "";

  /**
   * 日志类型。
   */
  public enum LogType {
    /** 记录原值和新值 */
    ALL,
    /** 记录原值 */
    ORIG,
    /** 记录新值 */
    NEW;
  }
}

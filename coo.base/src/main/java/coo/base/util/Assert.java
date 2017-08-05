package coo.base.util;

import java.util.Collection;
import java.util.Map;

import coo.base.exception.UncheckedException;

/**
 * 断言工具类。该工具类常用于对参数进行校验。
 */
public class Assert {
  /**
   * 断言表达式为true。
   * 
   * @param expression 表达式
   * @param message 错误信息
   */
  public static void isTrue(Boolean expression, String message) {
    if (!expression) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言表达式为true。
   * 
   * @param expression 表达式
   */
  public static void isTrue(Boolean expression) {
    isTrue(expression, "断言失败: 表达式必须是true");
  }

  /**
   * 断言对象为null。
   * 
   * @param object 待判断的对象
   * @param message 错误信息
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言对象为null。
   * 
   * @param object 待判断的对象
   */
  public static void isNull(Object object) {
    isNull(object, "断言失败: 对象必须是null");
  }

  /**
   * 断言对象不为null。
   * 
   * @param object 待判断的对象
   * @param message 错误信息
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言对象不为null。
   * 
   * @param object 待判断的对象
   */
  public static void notNull(Object object) {
    notNull(object, "断言失败: 对象不能是null");
  }

  /**
   * 断言字符串为空。
   * 
   * @param text 字符串
   * @param message 错误信息
   */
  public static void isEmpty(String text, String message) {
    if (StringUtils.isNotEmpty(text)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言字符串为空。
   * 
   * @param text 字符串
   */
  public static void isEmpty(String text) {
    isEmpty(text, "断言失败: 字符串必须是空");
  }

  /**
   * 断言字符串不为空。
   * 
   * @param text 字符串
   * @param message 错误信息
   */
  public static void notEmpty(String text, String message) {
    if (StringUtils.isEmpty(text)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言字符串不为空。
   * 
   * @param text 字符串
   */
  public static void notEmpty(String text) {
    notEmpty(text, "断言失败: 字符串不能是空");
  }

  /**
   * 断言字符串为空字符串。
   * 
   * @param text 字符串
   * @param message 错误信息
   */
  public static void isBlank(String text, String message) {
    if (StringUtils.isNotBlank(text)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言字符串为空字符串。
   * 
   * @param text 字符串
   */
  public static void isBlank(String text) {
    isBlank(text, "断言失败: 字符串必须是空字符串");
  }

  /**
   * 断言字符串不为空字符串。
   * 
   * @param text 字符串
   * @param message 错误信息
   */
  public static void notBlank(String text, String message) {
    if (StringUtils.isBlank(text)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言字符串不为空字符串。
   * 
   * @param text 字符串
   */
  public static void notBlank(String text) {
    notBlank(text, "断言失败: 字符串不能是空字符串");
  }

  /**
   * 断言集合不为空。
   * 
   * @param collection 待判断的集合
   * @param message 错误信息
   */
  public static void notEmpty(Collection<?> collection, String message) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言集合不为空。
   * 
   * @param collection 待判断的集合
   */
  public static void notEmpty(Collection<?> collection) {
    notEmpty(collection, "断言失败: 集合不能是空");
  }

  /**
   * 断言数组不为空。
   * 
   * @param array 待判断的数组
   * @param message 错误信息
   */
  public static void notEmpty(Object[] array, String message) {
    if (CollectionUtils.isEmpty(array)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言数组不为空。
   * 
   * @param array 待判断的数组
   */
  public static void notEmpty(Object[] array) {
    notEmpty(array, "断言失败: 数组不能是空");
  }

  /**
   * 断言Map不为空。
   * 
   * @param map 待判断的Map
   * @param message 错误信息
   */
  public static void notEmpty(Map<?, ?> map, String message) {
    if (CollectionUtils.isEmpty(map)) {
      throw new UncheckedException(message);
    }
  }

  /**
   * 断言Map不为空。
   * 
   * @param map 待判断的Map
   */
  public static void notEmpty(Map<?, ?> map) {
    notEmpty(map, "断言失败: Map不能是空");
  }

  /**
   * 私有构造方法。
   */
  private Assert() {}
}

package coo.base.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import coo.base.constants.Chars;
import coo.base.exception.UncheckedException;

/**
 * Bean工具类。用于直接操作类、对象的属性或方法。
 */
public abstract class BeanUtils {
  /**
   * 获取类中指定名称的属性，支持多层级。
   * 
   * @param targetClass 类
   * @param fieldName 属性名
   * @return 返回对应的属性，如果没找到返回null。
   */
  public static Field findField(Class<?> targetClass, String fieldName) {
    Assert.notNull(targetClass);
    Assert.notBlank(fieldName);
    if (fieldName.contains(".")) {
      return findNestedField(targetClass, fieldName);
    } else {
      return findDirectField(targetClass, fieldName);
    }
  }

  /**
   * 获取类中注有指定标注的属性集合。
   * 
   * @param targetClass 类
   * @param annotationClassOnField 标注
   * @return 返回注有指定标注的属性集合。
   */
  public static List<Field> findField(Class<?> targetClass,
      Class<? extends Annotation> annotationClassOnField) {
    Assert.notNull(targetClass);
    Assert.notNull(annotationClassOnField);
    List<Field> fields = new ArrayList<Field>();
    for (Field field : getAllDeclaredField(targetClass)) {
      if (field.isAnnotationPresent(annotationClassOnField)) {
        fields.add(field);
      }
    }
    return fields;
  }

  /**
   * 获取对象中指定属性的值。
   * 
   * @param target 对象
   * @param field 属性
   * @return 返回对象中指定属性的值。
   */
  public static Object getField(Object target, Field field) {
    if (field == null) {
      return null;
    }
    try {
      boolean accessible = field.isAccessible();
      field.setAccessible(true);
      Object result = field.get(target);
      field.setAccessible(accessible);
      return processHibernateLazyField(result);
    } catch (Exception e) {
      throw new UncheckedException("获取对象的属性[" + field.getName() + "]值失败", e);
    }
  }

  /**
   * 获取对象中指定属性的值，支持多层级。
   * 
   * @param target 对象
   * @param fieldName 属性名
   * @return 返回对象中指定属性的值。
   */
  public static Object getField(Object target, String fieldName) {
    Assert.notNull(target);
    Assert.notBlank(fieldName);
    if (fieldName.contains(".")) {
      return getNestedField(target, fieldName);
    } else {
      return getDirectField(target, fieldName);
    }
  }

  /**
   * 设置对象中指定属性的值。
   * 
   * @param target 对象
   * @param field 属性
   * @param value 值
   */
  public static void setField(Object target, Field field, Object value) {
    try {
      boolean accessible = field.isAccessible();
      field.setAccessible(true);
      field.set(target, value);
      field.setAccessible(accessible);
    } catch (Exception e) {
      throw new IllegalStateException("设置对象的属性[" + field.getName() + "]值失败", e);
    }
  }

  /**
   * 设置对象中指定属性的值，支持多层级。
   * 
   * @param target 对象
   * @param fieldName 属性名
   * @param value 值
   */
  public static void setField(Object target, String fieldName, Object value) {
    if (fieldName.contains(".")) {
      setNestedField(target, fieldName, value);
    } else {
      setDirectField(target, fieldName, value);
    }
  }

  /**
   * 获取指定类所有的公共属性列表。
   * 
   * @param targetClass 类
   * @param excludeFieldNames 排除的属性名称
   * @return 返回Field列表。
   */
  public static List<Field> getAllDeclaredField(Class<?> targetClass, String... excludeFieldNames) {
    List<Field> fields = new ArrayList<Field>();
    for (Field field : targetClass.getDeclaredFields()) {
      if (CollectionUtils.contains(excludeFieldNames, field.getName())) {
        continue;
      }
      fields.add(field);
    }
    Class<?> parentClass = targetClass.getSuperclass();
    if (parentClass != Object.class) {
      fields.addAll(getAllDeclaredField(parentClass, excludeFieldNames));
    }
    return fields;
  }

  /**
   * 获取指定类非static、final的公共属性列表。
   * 
   * @param targetClass 类
   * @param excludeFieldNames 排除的属性名称
   * @return 返回Field列表。
   */
  public static List<Field> getDeclaredFields(Class<?> targetClass, String... excludeFieldNames) {
    List<Field> fields = new ArrayList<Field>();
    for (Field field : getAllDeclaredField(targetClass, excludeFieldNames)) {
      if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
        fields.add(field);
      }
    }
    return fields;
  }

  /**
   * 复制两个对象相同Field的值，忽略源对象中为null的Field。
   * 
   * @param source 源对象
   * @param target 目标对象
   */
  public static void copyFields(Object source, Object target) {
    copyFields(source, target, null, null);
  }

  /**
   * 复制两个对象相同Field的值，忽略源对象中为null的Field。
   * 
   * @param source 源对象
   * @param target 目标对象
   * @param excludeFields 不复制的Field的名称，多个名称之间用“,”分割
   */
  public static void copyFields(Object source, Object target, String excludeFields) {
    copyFields(source, target, null, excludeFields);
  }

  /**
   * 复制两个对象相同Field的值，忽略源对象中为null的Field，但如果指定了要复制的Field，则为null时该Field也复制。
   * 
   * @param source 源对象
   * @param target 目标对象
   * @param includeFields 要复制的Field的名称，多个名称之间用“,”分割
   * @param excludeFields 不复制的Field的名称，多个名称之间用“,”分割
   */
  public static void copyFields(Object source, Object target, String includeFields,
      String excludeFields) {
    String[] includeFieldNames = new String[] {};
    if (!StringUtils.isBlank(includeFields)) {
      includeFieldNames = includeFields.split(Chars.COMMA);
    }
    String[] excludeFieldNames = new String[] {};
    if (!StringUtils.isBlank(excludeFields)) {
      excludeFieldNames = excludeFields.split(Chars.COMMA);
    }
    for (Field field : getAllDeclaredField(source.getClass(), excludeFieldNames)) {
      if (CollectionUtils.contains(includeFieldNames, field.getName())) {
        copyField(source, target, field.getName(), true);
      } else {
        copyField(source, target, field.getName(), false);
      }
    }
  }

  /**
   * 复制两个对象指定Field的值。
   * 
   * @param source 源对象
   * @param target 目标对象
   * @param fieldName Field的名称
   * @param containedNull 是否复制null值
   */
  @SuppressWarnings("unchecked")
  public static void copyField(Object source, Object target, String fieldName,
      Boolean containedNull) {
    Object sourceFieldValue = getField(source, fieldName);
    Boolean needCopy = isFieldNeedCopy(source, target, fieldName);
    if (sourceFieldValue == null && !containedNull) {
      needCopy = false;
    }
    if (needCopy) {
      // 处理Collection类型的属性
      if (sourceFieldValue != null
          && Collection.class.isAssignableFrom(sourceFieldValue.getClass())) {
        if (!((Collection<Object>) sourceFieldValue).isEmpty() || containedNull) {
          CollectionUtils.copy((Collection<Object>) sourceFieldValue,
              (Collection<Object>) getField(target, fieldName));
        }
      } else {
        setField(target, fieldName, sourceFieldValue);
      }
    }
  }

  /**
   * 获取Field的类型。
   * 
   * @param field Field
   * @return 返回Field的类型。
   */
  public static Class<?> getFieldType(Field field) {
    Class<?> fieldType = field.getType();
    if (Collection.class.isAssignableFrom(fieldType)) {
      Type fc = field.getGenericType();
      if (fc instanceof ParameterizedType) {
        ParameterizedType pt = (ParameterizedType) fc;
        fieldType = (Class<?>) pt.getActualTypeArguments()[0];
      }
    }
    return fieldType;
  }

  /**
   * 获取泛型Field的泛型类型。
   * 
   * @param field 泛型Field
   * @return 返回泛型Field的泛型类型。
   */
  public static Class<?> getGenericFieldType(Field field) {
    Type type = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
    if (type instanceof ParameterizedType) {
      return (Class<?>) ((ParameterizedType) type).getRawType();
    } else {
      return (Class<?>) type;
    }
  }

  /**
   * 复制Bean对象属性到Map对象。
   * 
   * @param bean Bean对象
   * @param map Map对象
   */
  public static void copyProperties(Object bean, Map<String, Object> map) {
    copyProperties(bean, map, null);
  }

  /**
   * 复制Bean对象属性到Map对象。
   * 
   * @param bean Bean对象
   * @param map Map对象
   * @param excludeFields 不复制的Field的名称，多个名称之间用“,”分割
   */
  public static void copyProperties(Object bean, Map<String, Object> map, String excludeFields) {
    try {
      String[] excludeFieldNames = new String[] {};
      if (!StringUtils.isBlank(excludeFields)) {
        excludeFieldNames = excludeFields.split(Chars.COMMA);
      }
      for (Field field : getAllDeclaredField(bean.getClass(), excludeFieldNames)) {
        if (!CollectionUtils.contains(excludeFieldNames, field.getName())) {
          map.put(field.getName(), getField(bean, field));
        }
      }
    } catch (Exception e) {
      throw new UncheckedException("复制Bean对象属性到Map对象时发生异常。", e);
    }
  }

  /**
   * 复制Map对象属性到Bean对象。
   * 
   * @param map Map对象
   * @param bean Bean对象
   */
  public static void copyProperties(Map<String, Object> map, Object bean) {
    copyProperties(map, bean, null);
  }

  /**
   * 复制Map对象属性到Bean对象。
   * 
   * @param map Map对象
   * @param bean Bean对象
   * @param excludeFields 不复制的Field的名称，多个名称之间用“,”分割
   * 
   */
  public static void copyProperties(Map<String, Object> map, Object bean, String excludeFields) {
    try {
      String[] excludeFieldNames = new String[] {};
      if (!StringUtils.isBlank(excludeFields)) {
        excludeFieldNames = excludeFields.split(Chars.COMMA);
      }
      for (Map.Entry<String, Object> entity : map.entrySet()) {
        Field field = findField(bean.getClass(), entity.getKey());
        if (field != null && !CollectionUtils.contains(excludeFieldNames, entity.getKey())) {
          setField(bean, field, entity.getValue());
        }
      }
    } catch (Exception e) {
      throw new UncheckedException("复制Map对象属性到Bean对象时发生异常。", e);
    }
  }

  /**
   * 判断指定的Field是否需要复制。
   * 
   * @param source 源对象
   * @param target 目标对象
   * @param fieldName Field的名称
   * @return 返回指定的Field是否需要复制。
   */
  private static Boolean isFieldNeedCopy(Object source, Object target, String fieldName) {
    Field sourceField = findField(source.getClass(), fieldName);
    Field targetField = findField(target.getClass(), fieldName);
    return targetField != null && sourceField.getType() == targetField.getType()
        && !Modifier.isFinal(targetField.getModifiers())
        && !Modifier.isStatic(targetField.getModifiers());
  }

  /**
   * 获取类中指定名称的单层级属性。
   * 
   * @param targetClass 类
   * @param fieldName 属性名
   * @return 返回对应的属性，如果没找到返回null。
   */
  private static Field findDirectField(Class<?> targetClass, String fieldName) {
    Assert.notNull(targetClass);
    Assert.notBlank(fieldName);
    for (Field field : getAllDeclaredField(targetClass)) {
      if (fieldName.equals(field.getName())) {
        return field;
      }
    }
    return null;
  }

  /**
   * 获取类中指定名称的多层级属性。
   * 
   * @param targetClass 类
   * @param fieldName 属性名
   * @return 返回对应的属性，如果没找到返回null。
   */
  private static Field findNestedField(Class<?> targetClass, String fieldName) {
    Assert.notNull(targetClass);
    Assert.notBlank(fieldName);
    String[] nestedFieldNames = fieldName.split("\\.");
    Field field = null;
    for (String nestedFieldName : nestedFieldNames) {
      field = findDirectField(targetClass, nestedFieldName);
      targetClass = field.getType();
    }
    return field;
  }

  /**
   * 获取对象中指定单层级属性的值。
   * 
   * @param target 对象
   * @param fieldName 属性名
   * @return 返回对象中指定属性的值。
   */
  private static Object getDirectField(Object target, String fieldName) {
    Assert.notNull(target);
    Assert.notBlank(fieldName);
    return getField(target, findDirectField(target.getClass(), fieldName));
  }

  /**
   * 获取对象中指定多层级属性的值。
   * 
   * @param target 对象
   * @param fieldName 属性名
   * @return 返回对象中指定属性的值。
   */
  private static Object getNestedField(Object target, String fieldName) {
    Assert.notNull(target);
    Assert.notBlank(fieldName);
    String[] nestedFieldNames = fieldName.split("\\.");
    for (String nestedFieldName : nestedFieldNames) {
      target = getDirectField(target, nestedFieldName);
    }
    return target;
  }

  /**
   * 设置对象中指定单层级属性的值。
   * 
   * @param target 对象
   * @param fieldName 属性名
   * @param value 值
   */
  private static void setDirectField(Object target, String fieldName, Object value) {
    setField(target, findDirectField(target.getClass(), fieldName), value);
  }

  /**
   * 设置对象中指定多层级属性的值。
   * 
   * @param target 对象
   * @param fieldName 属性名
   * @param value 值
   */
  private static void setNestedField(Object target, String fieldName, Object value) {
    String[] nestedFieldNames = StringUtils.substringBeforeLast(fieldName, ".").split("\\.");
    for (String nestedFieldName : nestedFieldNames) {
      target = getDirectField(target, nestedFieldName);
    }
    setDirectField(target, StringUtils.substringAfterLast(fieldName, "."), value);
  }

  /**
   * 处理Hibernate懒加载属性。
   * 
   * @param fieldValue 属性值
   * @return 如果是Hibernate懒加载属性则执行代理方法返回实际的属性对象，否则直接返回。
   */
  private static Object processHibernateLazyField(Object fieldValue) {
    try {
      Class<?> hibernateProxyClass = Class.forName("org.hibernate.proxy.HibernateProxy");
      if (hibernateProxyClass.isAssignableFrom(fieldValue.getClass())) {
        Method method = fieldValue.getClass().getMethod("getHibernateLazyInitializer");
        Object lazyInitializer = method.invoke(fieldValue);
        method = lazyInitializer.getClass().getMethod("getImplementation");
        return method.invoke(lazyInitializer);
      } else {
        return fieldValue;
      }
    } catch (Exception e) {
      return fieldValue;
    }
  }
}

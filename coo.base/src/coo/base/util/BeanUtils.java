package coo.base.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
	 * 获取类中指定名称的属性。
	 * 
	 * @param targetClass
	 *            类
	 * @param fieldName
	 *            属性名
	 * @return 返回对应的属性，如果没找到返回null。
	 */
	public static Field findField(Class<?> targetClass, String fieldName) {
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
	 * 获取类中注有指定标注的属性集合。
	 * 
	 * @param targetClass
	 *            类
	 * @param annotationClassOnField
	 *            标注
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
	 * @param target
	 *            对象
	 * @param field
	 *            属性
	 * @return 返回对象中指定属性的值。
	 */
	public static Object getField(Object target, Field field) {
		try {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			Object result = field.get(target);
			field.setAccessible(accessible);
			return result;
		} catch (Exception e) {
			throw new IllegalStateException("获取对象的属性[" + field.getName()
					+ "]值失败", e);
		}
	}

	/**
	 * 获取对象中指定属性的值。
	 * 
	 * @param target
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return 返回对象中指定属性的值。
	 */
	public static Object getField(Object target, String fieldName) {
		return getField(target, findField(target.getClass(), fieldName));
	}

	/**
	 * 设置对象中指定属性的值。
	 * 
	 * @param target
	 *            对象
	 * @param field
	 *            属性
	 * @param value
	 *            值
	 */
	public static void setField(Object target, Field field, Object value) {
		try {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			field.set(target, value);
			field.setAccessible(accessible);
		} catch (Exception e) {
			throw new IllegalStateException("设置对象的属性[" + field.getName()
					+ "]值失败", e);
		}
	}

	/**
	 * 设置对象中指定属性的值。
	 * 
	 * @param target
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @param value
	 *            值
	 */
	public static void setField(Object target, String fieldName, Object value) {
		setField(target, findField(target.getClass(), fieldName), value);
	}

	/**
	 * 递归获取类的Field，直到其父类为Object类。子类的Field排列在父类之前。
	 * 
	 * @param targetClass
	 *            类
	 * @param excludeFieldNames
	 *            排除的属性名称
	 * @return 返回Field列表。
	 */
	public static List<Field> getAllDeclaredField(Class<?> targetClass,
			String... excludeFieldNames) {
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
	 * 复制两个对象相同Field的值，忽略源对象中为null的Field。
	 * 
	 * @param <T>
	 *            对象类型
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	public static <T> void copyFields(T source, T target) {
		copyFields(source, target, null, null);
	}

	/**
	 * 复制两个对象相同Field的值，忽略源对象中为null的Field。
	 * 
	 * @param <T>
	 *            对象类型
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param excludeFields
	 *            不复制的Field的名称，多个名称之间用“,”分割
	 */
	public static <T> void copyFields(T source, T target, String excludeFields) {
		copyFields(source, target, null, excludeFields);
	}

	/**
	 * 复制两个对象相同Field的值，忽略源对象中为null的Field，但如果指定了要复制的Field，则为null时该Field也复制。
	 * 
	 * @param <T>
	 *            对象类型
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param includeFields
	 *            要复制的Field的名称，多个名称之间用“,”分割
	 * @param excludeFields
	 *            不复制的Field的名称，多个名称之间用“,”分割
	 */
	public static <T> void copyFields(T source, T target, String includeFields,
			String excludeFields) {
		String[] includeFieldNames = new String[] {};
		if (!StringUtils.isBlank(includeFields)) {
			includeFieldNames = includeFields.split(Chars.COMMA);
		}
		String[] excludeFieldNames = new String[] {};
		if (!StringUtils.isBlank(excludeFields)) {
			excludeFieldNames = excludeFields.split(Chars.COMMA);
		}
		for (Field field : getAllDeclaredField(source.getClass(),
				excludeFieldNames)) {
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
	 * @param <T>
	 *            对象类型
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param fieldName
	 *            Field的名称
	 * @param containedNull
	 *            是否复制null值
	 */
	@SuppressWarnings("unchecked")
	public static <T> void copyField(T source, T target, String fieldName,
			Boolean containedNull) {
		Object sourceFieldValue = getField(source, fieldName);
		Boolean needCopy = findField(target.getClass(), fieldName) != null;
		if (needCopy && !containedNull && sourceFieldValue == null) {
			needCopy = false;
		}
		if (needCopy) {
			if (sourceFieldValue != null
					&& Collection.class.isAssignableFrom(sourceFieldValue
							.getClass())) {
				CollectionUtils.copy((Collection<Object>) sourceFieldValue,
						(Collection<Object>) getField(target, fieldName));
			} else {
				setField(target, fieldName, sourceFieldValue);
			}
		}
	}

	/**
	 * 获取泛型Field的泛型类型。
	 * 
	 * @param field
	 *            泛型Field
	 * @return 返回泛型Field的泛型类型。
	 */
	public static Class<?> getGenericFieldType(Field field) {
		Type type = ((ParameterizedType) field.getGenericType())
				.getActualTypeArguments()[0];
		if (type instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) type).getRawType();
		} else {
			return (Class<?>) type;
		}
	}

	/**
	 * 复制Bean对象属性到Map对象。
	 * 
	 * @param bean
	 *            Bean对象
	 * @param map
	 *            Map对象
	 */
	public static void copyProperties(Object bean, Map<String, Object> map) {
		copyProperties(bean, map, null);
	}

	/**
	 * 复制Bean对象属性到Map对象。
	 * 
	 * @param bean
	 *            Bean对象
	 * @param map
	 *            Map对象
	 * @param excludeFields
	 *            不复制的Field的名称，多个名称之间用“,”分割
	 */
	public static void copyProperties(Object bean, Map<String, Object> map,
			String excludeFields) {
		try {
			String[] excludeFieldNames = new String[] {};
			if (!StringUtils.isBlank(excludeFields)) {
				excludeFieldNames = excludeFields.split(Chars.COMMA);
			}
			for (Field field : getAllDeclaredField(bean.getClass(),
					excludeFieldNames)) {
				if (!CollectionUtils.contains(excludeFieldNames,
						field.getName())) {
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
	 * @param map
	 *            Map对象
	 * @param bean
	 *            Bean对象
	 */
	public static void copyProperties(Map<String, Object> map, Object bean) {
		copyProperties(map, bean, null);
	}

	/**
	 * 复制Map对象属性到Bean对象。
	 * 
	 * @param map
	 *            Map对象
	 * @param bean
	 *            Bean对象
	 * @param excludeFields
	 *            不复制的Field的名称，多个名称之间用“,”分割
	 * 
	 */
	public static void copyProperties(Map<String, Object> map, Object bean,
			String excludeFields) {
		try {
			String[] excludeFieldNames = new String[] {};
			if (!StringUtils.isBlank(excludeFields)) {
				excludeFieldNames = excludeFields.split(Chars.COMMA);
			}
			for (Map.Entry<String, Object> entity : map.entrySet()) {
				Field field = findField(bean.getClass(), entity.getKey());
				if (field != null
						&& !CollectionUtils.contains(excludeFieldNames,
								entity.getKey())) {
					setField(bean, field, entity.getValue());
				}
			}
		} catch (Exception e) {
			throw new UncheckedException("复制Map对象属性到Bean对象时发生异常。", e);
		}
	}
}

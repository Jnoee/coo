package coo.core.model;

/**
 * 枚举类型接口，实现该接口的类必须是枚举类。
 */
public interface IEnum {
	/**
	 * 获取枚举类型实例要显示的文本。
	 * 
	 * @return 返回枚举类型实例的文本。
	 */
	String getText();

	/**
	 * 获取枚举类型实例的值。
	 * 
	 * @return 返回枚举类型实例的值。
	 */
	String getValue();
}

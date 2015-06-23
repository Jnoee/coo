package coo.core.enums;

import coo.base.constants.Color;
import coo.core.model.IEnum;

/**
 * 启用状态。
 */
public enum EnabledStatus implements IEnum {
	DISABLED("停用", "0", Color.GRAY), ENABLED("启用", "1", Color.GREEN);

	private String text;
	private String value;
	private String color;

	/**
	 * 构造方法
	 * 
	 * @param text
	 *            文本
	 * @param value
	 *            值
	 * @param color
	 *            颜色
	 */
	private EnabledStatus(String text, String value, String color) {
		this.text = text;
		this.value = value;
		this.color = color;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getValue() {
		return value;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return text;
	}
}

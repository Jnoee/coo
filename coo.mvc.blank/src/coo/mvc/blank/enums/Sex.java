package coo.mvc.blank.enums;

import coo.core.model.IEnum;

public enum Sex implements IEnum {
	MALE("男", "1"), FEMALE("女", "2");

	private String text;
	private String value;

	private Sex(String text, String value) {
		this.text = text;
		this.value = value;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getValue() {
		return value;
	}
}

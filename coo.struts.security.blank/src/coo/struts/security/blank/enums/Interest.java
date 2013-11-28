package coo.struts.security.blank.enums;

import coo.core.model.IEnum;

public enum Interest implements IEnum {
	FOOTBALL("足球", "1"), BASKETBALL("篮球", "2"), BADMINTON("羽毛球", "3"), PONG(
			"乒乓球", "4"), OTHER("其他", "5");

	private String text;
	private String value;

	private Interest(String text, String value) {
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
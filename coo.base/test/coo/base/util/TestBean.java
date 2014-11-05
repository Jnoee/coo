package coo.base.util;

import java.io.Serializable;

public class TestBean implements Serializable {
	private static final long serialVersionUID = 1;
	private static String STATIC_FIELD = "STATIC_FIELD";
	private final String finalField = "finalField";
	private String name;

	public String getName() {
		return name + finalField;
	}

	public void setName(String name) {
		this.name = name + STATIC_FIELD;
	}
}

package coo.core.security.model;

/**
 * 日志数据项。用于数据比较。
 */
public class LogData {
	private String text;
	private String origData;
	private String newData;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOrigData() {
		return origData;
	}

	public void setOrigData(String origData) {
		this.origData = origData;
	}

	public String getNewData() {
		return newData;
	}

	public void setNewData(String newData) {
		this.newData = newData;
	}

	public Boolean isChanged() {
		return newData != null && origData != null && !origData.equals(newData);
	}
}

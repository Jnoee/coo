package coo.core.report.jxls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel数据。
 */
public class ExcelData {
	/** 模版文件名称 */
	private String templateFileName;
	/** 工作表数据模型列表 */
	private List<?> sheetModels;
	/** 工作表名称列表 */
	private List<String> sheetNames = new ArrayList<String>();
	/** 工作表名称字段 */
	private String sheetNameField = "name";
	/** 工作表数据模型名称 */
	private String sheetModelName;
	/** 除工作表模型外的数据模型 */
	private Map<String, ?> otherModel;
	/** 多工作表开始位置 */
	private Integer startSheetNum = 0;

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public List<?> getSheetModels() {
		return sheetModels;
	}

	public void setSheetModels(List<?> sheetModels) {
		this.sheetModels = sheetModels;
	}

	public List<String> getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(List<String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String getSheetNameField() {
		return sheetNameField;
	}

	public void setSheetNameField(String sheetNameField) {
		this.sheetNameField = sheetNameField;
	}

	public String getSheetModelName() {
		return sheetModelName;
	}

	public void setSheetModelName(String sheetModelName) {
		this.sheetModelName = sheetModelName;
	}

	public Map<String, ?> getOtherModel() {
		return otherModel;
	}

	public void setOtherModel(Map<String, ?> otherModel) {
		this.otherModel = otherModel;
	}

	public Integer getStartSheetNum() {
		return startSheetNum;
	}

	public void setStartSheetNum(Integer startSheetNum) {
		this.startSheetNum = startSheetNum;
	}
}

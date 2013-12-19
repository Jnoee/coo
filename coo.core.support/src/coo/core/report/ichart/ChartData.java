package coo.core.report.ichart;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 图表数据。
 */
public class ChartData {
	/** 名称 */
	private String name;
	/** 数值列表 */
	@JsonProperty("value")
	private List<Number> values = new ArrayList<Number>();
	/** 颜色 */
	private String color;
	/** 区域颜色 */
	@JsonProperty("area_color")
	private String areaColor;
	/** 线宽 */
	@JsonProperty("line_width")
	private Integer lineWidth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Number> getValues() {
		return values;
	}

	public void setValues(List<Number> values) {
		this.values = values;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(String areaColor) {
		this.areaColor = areaColor;
	}

	public Integer getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}
}

package coo.core.report.ichart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import coo.base.exception.UncheckedException;

/**
 * 该类用于承载图表数据。
 */
public class Chart {
	/** 图表数据列表 */
	private List<ChartData> datas = new ArrayList<ChartData>();
	/** 图表标签列表 */
	private List<String> labels = new ArrayList<String>();

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            数值
	 * @param color
	 *            颜色
	 */
	public void addData(String name, Number value, String color) {
		addData(name, new Number[] { value }, color);
	}

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param values
	 *            数值数组
	 * @param color
	 *            颜色
	 */
	public void addData(String name, Number[] values, String color) {
		addData(name, values, color, null);
	}

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param values
	 *            数值列表
	 * @param color
	 *            颜色
	 */
	public void addData(String name, List<Number> values, String color) {
		addData(name, values, color, null);
	}

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param values
	 *            数值数组
	 * @param color
	 *            颜色
	 * @param lineWidth
	 *            线宽
	 */
	public void addData(String name, Number[] values, String color,
			Integer lineWidth) {
		addData(name, values, color, null, lineWidth);
	}

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param values
	 *            数值列表
	 * @param color
	 *            颜色
	 * @param lineWidth
	 *            线宽
	 */
	public void addData(String name, List<Number> values, String color,
			Integer lineWidth) {
		addData(name, values, color, null, lineWidth);
	}

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param values
	 *            数值数组
	 * @param color
	 *            颜色
	 * @param areaColor
	 *            区域颜色
	 * @param lineWidth
	 *            线宽
	 */
	public void addData(String name, Number[] values, String color,
			String areaColor, Integer lineWidth) {
		addData(name, Arrays.asList(values), color, areaColor, lineWidth);
	}

	/**
	 * 新增图表数据。
	 * 
	 * @param name
	 *            名称
	 * @param values
	 *            数值列表
	 * @param color
	 *            颜色
	 * @param areaColor
	 *            区域颜色
	 * @param lineWidth
	 *            线宽
	 */
	public void addData(String name, List<Number> values, String color,
			String areaColor, Integer lineWidth) {
		ChartData data = new ChartData();
		data.setName(name);
		data.setValues(values);
		data.setColor(color);
		data.setAreaColor(areaColor);
		data.setLineWidth(lineWidth);
		datas.add(data);
	}

	/**
	 * 新增标签。
	 * 
	 * @param labels
	 *            标签
	 */
	public void addLabel(String... labels) {
		for (String label : labels) {
			this.labels.add(label);
		}
	}

	/**
	 * 获取数据的JSON字符串。
	 * 
	 * @return 返回数据的JSON字符串。
	 */
	public String getDatas() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
			mapper.setSerializationInclusion(Include.NON_NULL);
			if (datas.size() == 1) {
				return "[ " + mapper.writeValueAsString(datas) + " ]";
			} else {
				return mapper.writeValueAsString(datas);
			}
		} catch (Exception e) {
			throw new UncheckedException("将图表数据列表转换成JSON字符串时发生异常。", e);
		}
	}

	/**
	 * 获取标签的JSON字符串。
	 * 
	 * @return 返回标签的JSON字符串。
	 */
	public String getLabels() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.setSerializationInclusion(Include.NON_NULL);
			return mapper.writeValueAsString(labels);
		} catch (Exception e) {
			throw new UncheckedException("将图表标签列表转换成JSON字符串时发生异常。", e);
		}
	}
}

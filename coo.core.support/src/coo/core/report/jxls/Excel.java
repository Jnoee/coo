package coo.core.report.jxls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;

/**
 * Excel包装类。
 */
public class Excel {
	/** Excel模版路径 */
	private static String REPORT_DIR = "/META-INF/coo/report";
	private XLSTransformer transformer = new XLSTransformer();
	private Workbook workbook;

	/**
	 * 初始化生成Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param model
	 *            数据模型
	 */
	public void init(String templateFileName, Map<String, ?> model) {
		try {
			InputStream in = getClass().getResourceAsStream(
					REPORT_DIR + "/" + templateFileName);
			workbook = transformer.transformXLS(in, model);
			in.close();
		} catch (Exception e) {
			throw new UncheckedException("生成Excel时发生异常。", e);
		}
	}

	/**
	 * 初始化生成多工作表的Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param sheetModels
	 *            工作表数据模型列表
	 * @param sheetModelName
	 *            工作表数据模型名称
	 */
	public void init(String templateFileName, List<?> sheetModels,
			String sheetModelName) {
		init(templateFileName, sheetModels, "name", sheetModelName,
				new HashMap<String, Object>(), 0);
	}

	/**
	 * 初始化生成多工作表的Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param sheetModels
	 *            工作表数据模型列表
	 * @param sheetModelName
	 *            工作表数据模型名称
	 * @param otherModel
	 *            除工作表模型外的数据模型
	 * @param startSheetNum
	 *            多工作表开始位置
	 */
	public void init(String templateFileName, List<?> sheetModels,
			String sheetModelName, Map<String, Object> otherModel,
			Integer startSheetNum) {
		init(templateFileName, sheetModels, "name", sheetModelName, otherModel,
				startSheetNum);
	}

	/**
	 * 初始化生成多工作表的Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param sheetModels
	 *            工作表数据模型列表
	 * @param sheetNameField
	 *            用作工作表名称的数据模型中对应的字段名
	 * @param sheetModelName
	 *            工作表数据模型名称
	 */
	public void init(String templateFileName, List<?> sheetModels,
			String sheetNameField, String sheetModelName) {
		init(templateFileName, sheetModels, sheetNameField, sheetModelName,
				new HashMap<String, Object>(), 0);
	}

	/**
	 * 初始化生成多工作表的Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param sheetModels
	 *            工作表数据模型列表
	 * @param sheetNameField
	 *            用作工作表名称的数据模型中对应的字段名
	 * @param sheetModelName
	 *            工作表数据模型名称
	 * @param otherModel
	 *            除工作表模型外的数据模型
	 * @param startSheetNum
	 *            多工作表开始位置
	 */
	public void init(String templateFileName, List<?> sheetModels,
			String sheetNameField, String sheetModelName,
			Map<String, Object> otherModel, Integer startSheetNum) {
		List<String> sheetNames = new ArrayList<String>();
		for (Object sheetModel : sheetModels) {
			sheetNames.add(BeanUtils.getField(sheetModel, sheetNameField)
					.toString());
		}
		init(templateFileName, sheetModels, sheetNames, sheetModelName,
				otherModel, startSheetNum);
	}

	/**
	 * 初始化生成多工作表的Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param sheetModels
	 *            工作表数据模型列表
	 * @param sheetNames
	 *            工作表名称列表
	 * @param sheetModelName
	 *            工作表数据模型名称
	 */
	public void init(String templateFileName, List<?> sheetModels,
			List<String> sheetNames, String sheetModelName) {
		init(templateFileName, sheetModels, sheetNames, sheetModelName,
				new HashMap<String, Object>(), 0);
	}

	/**
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param sheetModels
	 *            工作表数据模型列表
	 * @param sheetNames
	 *            工作表名称列表
	 * @param sheetModelName
	 *            工作表数据模型名称
	 * @param otherModel
	 *            除工作表模型外的数据模型
	 * @param startSheetNum
	 *            多工作表开始位置
	 */
	public void init(String templateFileName, List<?> sheetModels,
			List<String> sheetNames, String sheetModelName,
			Map<String, Object> otherModel, Integer startSheetNum) {
		try {
			InputStream in = getClass().getResourceAsStream(
					REPORT_DIR + "/" + templateFileName);
			workbook = transformer.transformMultipleSheetsList(in, sheetModels,
					sheetNames, sheetModelName, otherModel, startSheetNum);
			in.close();
		} catch (Exception e) {
			throw new UncheckedException("生成Excel时发生异常。", e);
		}
	}

	/**
	 * 转换成Excel文件输出流。
	 * 
	 * @return 返回Excel文件输出流。
	 */
	public ByteArrayOutputStream toOutputStream() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			out.flush();
			return out;
		} catch (Exception e) {
			throw new UncheckedException("转换Excel文件输出流时发生异常。", e);
		}
	}

	/**
	 * 转换成Excel文件输入流。
	 * 
	 * @return 返回Excel文件输入流。
	 */
	public ByteArrayInputStream toInputStream() {
		try {
			ByteArrayOutputStream out = toOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(
					out.toByteArray());
			out.close();
			return in;
		} catch (Exception e) {
			throw new UncheckedException("转换Excel文件输入流时发生异常。", e);
		}
	}

	/**
	 * 将Excel文件写入到输出流。
	 * 
	 * @param out
	 *            输出流
	 */
	public void writeTo(OutputStream out) {
		try {
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new UncheckedException("将Excel文件写入到输出流时发生异常。", e);
		}
	}

	public XLSTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(XLSTransformer transformer) {
		this.transformer = transformer;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
}

package coo.core.report.jxls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
	 * @param data
	 *            Excel数据
	 */
	public void init(ExcelData data) {
		try {
			// 如果没有设置工作表名称列表，则根据指定的工作表名称字段自动生成。
			List<String> sheetNames = data.getSheetNames();
			if (sheetNames.isEmpty()) {
				for (Object sheetModel : data.getSheetModels()) {
					sheetNames.add(BeanUtils.getField(sheetModel,
							data.getSheetNameField()).toString());
				}
			}
			InputStream in = getClass().getResourceAsStream(
					REPORT_DIR + "/" + data.getTemplateFileName());
			workbook = transformer.transformMultipleSheetsList(in,
					data.getSheetModels(), sheetNames,
					data.getSheetModelName(), data.getOtherModel(),
					data.getStartSheetNum());
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

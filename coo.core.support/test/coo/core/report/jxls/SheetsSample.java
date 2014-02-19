package coo.core.report.jxls;

import java.io.FileOutputStream;

import org.junit.Test;

public class SheetsSample extends AbstractSample {
	@Test
	public void test() throws Exception {
		Excel excel = new Excel();

		ExcelData data = new ExcelData();
		data.setTemplateFileName("sheets.xlsx");
		data.setSheetModels(genDepartments(3));
		data.setSheetModelName("department");

		excel.init(data);
		excel.writeTo(new FileOutputStream(outputDir + "/sheets_output.xlsx"));
	}
}
package coo.core.report.jxls;

import java.io.FileOutputStream;

import org.junit.Test;

public class SheetsSample extends AbstractSample {
	@Test
	public void test() throws Exception {
		Excel excel = new Excel();
		excel.init("sheets.xlsx", genDepartments(3), "department");
		excel.writeTo(new FileOutputStream(outputDir + "/sheets_output.xlsx"));
	}
}
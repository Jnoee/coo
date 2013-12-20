package coo.core.report.jxls;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BasicSample extends AbstractSample {
	@Test
	public void test() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("department", genDepartments(1).get(0));

		Excel excel = new Excel();
		excel.init("basic.xlsx", model);
		excel.writeTo(new FileOutputStream(outputDir + "/basic_output.xlsx"));
	}
}
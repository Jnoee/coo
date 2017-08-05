package coo.core.jxls;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import coo.core.jxls.Excel;

public class GroupingSample extends AbstractSample {
  @Test
  public void test() throws Exception {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("departments", genDepartments(3));

    Excel excel = new Excel();
    excel.init("grouping.xlsx", model);
    excel.writeTo(new FileOutputStream(outputDir + "/grouping_output.xlsx"));
  }
}

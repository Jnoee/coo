package coo.core.jxls;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import coo.core.jxls.Excel;

public class TagsSample extends AbstractSample {
  @Test
  public void test() throws Exception {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("departments", genDepartments(3));

    Excel excel = new Excel();
    excel.init("tags.xlsx", model);
    excel.writeTo(new FileOutputStream(outputDir + "/tags_output.xlsx"));
  }
}

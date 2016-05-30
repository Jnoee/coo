package coo.core.jxls;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import coo.core.jxls.Excel;

public class ChartSample extends AbstractSample {
  @Test
  public void test() throws Exception {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("employees", genEmployees(6, null));

    Excel excel = new Excel();
    excel.init("chart.xlsx", model);
    excel.writeTo(new FileOutputStream(outputDir + "/chart_output.xlsx"));
  }
}

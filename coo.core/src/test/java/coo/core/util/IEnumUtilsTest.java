package coo.core.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coo.core.model.IEnum;

public class IEnumUtilsTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void test() throws Exception {
    log.debug(IEnumUtils.getIEnumByValue(EntityType.class, "2").getText());
    log.debug(IEnumUtils.getIEnumByText(EntityType.class, "推荐舆情").getText());
  }

  public enum EntityType implements IEnum {
    MAINENTITY("ST存储数据", "1"), FAVORITES("我的收藏", "2"), COMMENDOPINION("推荐舆情", "3");

    private String text;
    private String value;

    /**
     * 构造方法。
     * 
     * @param text 枚举类的文本
     * @param value 枚举类的值
     */
    private EntityType(String text, String value) {
      this.text = text;
      this.value = value;
    }

    public String getText() {
      return text;
    }

    public String getValue() {
      return value;
    }
  }
}

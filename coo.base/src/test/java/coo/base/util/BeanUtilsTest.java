package coo.base.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtilsTest {
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void test() {
    NestedBean nestedBean1 = new NestedBean();
    nestedBean1.setName("nestedBean1");

    NestedBean nestedBean2 = new NestedBean();
    nestedBean2.setName("nestedBean2");

    NestedBean nestedBean3 = new NestedBean();
    nestedBean3.setName("nestedBean3");

    nestedBean1.setNestedBean(nestedBean2);
    nestedBean2.setNestedBean(nestedBean3);

    BeanUtils.setField(nestedBean1, "nestedBean.nestedBean.name", "changed name");

    log.debug("{}", BeanUtils.findField(NestedBean.class, "nestedBean.nestedBean.nestedBean.name"));
    log.debug("{}", BeanUtils.getField(nestedBean1, "nestedBean.nestedBean.name"));
  }
}

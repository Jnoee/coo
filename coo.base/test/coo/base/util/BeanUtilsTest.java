package coo.base.util;

import org.junit.Test;

public class BeanUtilsTest {
	@Test
	public void test() {
		TestBean bean1 = new TestBean();
		bean1.setName("bean1");

		TestBean bean2 = new TestBean();
		bean2.setName("bean2");

		BeanUtils.copyFields(bean1, bean2);
	}
}

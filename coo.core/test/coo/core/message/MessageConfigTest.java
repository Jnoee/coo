package coo.core.message;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:messageConfigTestContext.xml" })
public class MessageConfigTest {
	@Resource
	private MessageConfig messageConfig;

	@Test
	public void test() {
		String msg = messageConfig.getString("msg", "A", "B");
		Assert.assertEquals(msg, "文字+变量A+变量B");
	}
}

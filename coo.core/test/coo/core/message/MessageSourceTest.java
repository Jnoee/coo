package coo.core.message;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:messageSourceTestContext.xml" })
public class MessageSourceTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private MessageSource messageSource;

	@Test
	public void test() {
		String msg = messageSource.get("msg", "A", "B");
		Assert.assertEquals(msg, "文字+变量A+变量B");
		msg = messageSource.get("core", "A", "B");
		log.debug(msg);
		msg = messageSource.get("none", "A", "B");
		log.debug(msg);
	}
}

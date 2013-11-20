package coo.base.util;

import org.junit.Assert;
import org.junit.Test;

public class ThrowableUtilsTest {
	@Test
	public void testGetRootThrowable() {
		Exception firstException = new Exception();
		Exception secondException = new Exception(firstException);
		Exception thirdException = new Exception(secondException);
		Assert.assertEquals(firstException,
				ThrowableUtils.getRootThrowable(thirdException));
	}
}

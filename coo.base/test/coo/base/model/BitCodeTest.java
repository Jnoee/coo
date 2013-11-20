package coo.base.model;

import org.junit.Assert;
import org.junit.Test;

public class BitCodeTest {
	@Test
	public void test() {
		BitCode code1 = new BitCode(
				"10101010101010101010101010101011010101010101010101010101010101");
		BitCode code2 = new BitCode(
				"01010101010101010101010101010100101010101010101010101010101010");
		BitCode code3 = new BitCode(
				"01010101010101010101010101010100101010101010101010101010101010");
		Assert.assertEquals(code2, code3);
		code3 = code1.or(code2);
		Assert.assertEquals(code3,
				"11111111111111111111111111111111111111111111111111111111111111");
		Assert.assertTrue(code1.isTrue(1));
		Assert.assertFalse(code1.isTrue(2));
	}
}

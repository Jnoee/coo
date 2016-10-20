package coo.base.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {
  @Test
  public void testIsBlank() {
    assertTrue(StringUtils.isBlank("  "));
    assertTrue(StringUtils.isBlank(" "));
    assertTrue(StringUtils.isBlank(""));
    assertTrue(StringUtils.isBlank(null));
    assertFalse(StringUtils.isBlank(" a "));
  }

  @Test
  public void testJoin() {
    String[] strs = new String[] {"a", "b", "c"};
    assertEquals(StringUtils.join(strs, ","), "a,b,c");
    assertEquals(StringUtils.join(strs, ", "), "a, b, c");
  }
}

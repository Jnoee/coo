package coo.base.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {
  @Test
  public void testDistinct() {
    List<String> strs = genList();
    strs.add("a");
    CollectionUtils.distinct(strs);
    assertArrayEquals(strs.toArray(), genArray());
  }

  @Test
  public void testContains() {
    String[] strs = genArray();
    assertFalse(CollectionUtils.contains(null, "a"));
    assertTrue(CollectionUtils.contains(strs, "a"));
    assertFalse(CollectionUtils.contains(strs, "d"));
  }

  @Test
  public void testCopy() {
    List<String> strs = genList();
    List<String> cops = new ArrayList<>();
    CollectionUtils.copy(strs, cops);
    assertArrayEquals(strs.toArray(), cops.toArray());
  }

  private String[] genArray() {
    return new String[] {"a", "b", "c"};
  }

  private List<String> genList() {
    return CollectionUtils.toList(genArray());
  }
}

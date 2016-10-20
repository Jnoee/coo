package coo.base.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CollectionUtilsTest {
  @Test
  public void testDistinct() {
    List<String> strs = genList();
    strs.add("a");
    CollectionUtils.distinct(strs);
    Assert.assertArrayEquals(strs.toArray(), genArray());
  }

  @Test
  public void testContains() {
    String[] strs = genArray();
    Assert.assertFalse(CollectionUtils.contains(null, "a"));
    Assert.assertTrue(CollectionUtils.contains(strs, "a"));
    Assert.assertFalse(CollectionUtils.contains(strs, "d"));
  }

  @Test
  public void testCopy() {
    List<String> strs = genList();
    List<String> cops = new ArrayList<>();
    CollectionUtils.copy(strs, cops);
    Assert.assertArrayEquals(strs.toArray(), cops.toArray());
  }

  private String[] genArray() {
    return new String[] {"a", "b", "c"};
  }

  private List<String> genList() {
    return CollectionUtils.toList(genArray());
  }
}

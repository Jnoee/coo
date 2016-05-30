package coo.base.util;

import java.util.TreeMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoUtilsTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testBase64() {
    String content = "content";

    content = CryptoUtils.encodeBase64(content);
    log.debug(content);

    content = CryptoUtils.decodeBase64(content);
    log.debug(content);
  }

  @Test
  public void testMd5() {
    String content = "123456";

    content = CryptoUtils.md5(content);
    log.debug(content);
  }

  @Test
  public void testGenRandomCode() {
    TreeMap<String, String> map = new TreeMap<>();
    for (int i = 0; i < 20000; i++) {
      String key = CryptoUtils.genRandomCode("0123456789", 6);
      map.put(key, key);
    }
    log.debug("{}", map.keySet().size());
    log.debug("{}", map.keySet());
  }
}

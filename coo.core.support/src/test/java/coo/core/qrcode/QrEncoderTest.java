package coo.core.qrcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QrEncoderTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testBase64() {
    log.debug(QrEncoder.base64("测试文本", "jpg", 300));
  }
}

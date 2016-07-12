package coo.core.qrcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QrDecoderTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testDecode() {
    String code = QrEncoder.base64("测试文本", "jpg", 300);
    log.debug("{}", code);
    String result = QrDecoder.decode(code);
    log.debug("{}", result);
  }
}

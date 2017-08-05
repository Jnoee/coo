package coo.base.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtilsTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testHalfUp() {
    Double d1 = 2.3446D;
    Double d2 = 2.3455D;
    Double d3 = 2.3464D;
    log.debug("{}", NumberUtils.halfUp(d1, 1));
    log.debug("{}", NumberUtils.halfUp(d2, 1));
    log.debug("{}", NumberUtils.halfUp(d3, 1));
    log.debug("{}", NumberUtils.halfUp(d1));
    log.debug("{}", NumberUtils.halfUp(d2));
    log.debug("{}", NumberUtils.halfUp(d3));
    log.debug("{}", NumberUtils.halfUp(d1, 3));
    log.debug("{}", NumberUtils.halfUp(d2, 3));
    log.debug("{}", NumberUtils.halfUp(d3, 3));

    Float f1 = 2.3446F;
    Float f2 = 2.3455F;
    Float f3 = 2.3464F;
    log.debug("{}", NumberUtils.halfUp(f1, 1));
    log.debug("{}", NumberUtils.halfUp(f2, 1));
    log.debug("{}", NumberUtils.halfUp(f3, 1));
    log.debug("{}", NumberUtils.halfUp(f1));
    log.debug("{}", NumberUtils.halfUp(f2));
    log.debug("{}", NumberUtils.halfUp(f3));
    log.debug("{}", NumberUtils.halfUp(f1, 3));
    log.debug("{}", NumberUtils.halfUp(f2, 3));
    log.debug("{}", NumberUtils.halfUp(f3, 3));

    d1 = 9.99D;
    d2 = 3.34D;
    log.debug("{}", NumberUtils.add(d1, d2));
    log.debug("{}", NumberUtils.add(d1, d2, 3));
    log.debug("{}", NumberUtils.sub(d1, d2));
    log.debug("{}", NumberUtils.sub(d1, d2, 3));
    log.debug("{}", NumberUtils.mul(d1, d2));
    log.debug("{}", NumberUtils.mul(d1, d2, 3));
    log.debug("{}", NumberUtils.div(d1, d2));
    log.debug("{}", NumberUtils.div(d1, d2, 3));
  }
}

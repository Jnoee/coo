package coo.base.util;

import java.util.Date;

import org.joda.time.Duration;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtilsTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testGetToday() {
    Date today = DateUtils.getToday();
    log.debug(DateUtils.format(today, DateUtils.SECOND));

    Date nextDay = DateUtils.getNextDay();
    log.debug(DateUtils.format(nextDay, DateUtils.SECOND));

    String d1 = "2011-7-10";
    log.debug(DateUtils.format(DateUtils.parse(d1), DateUtils.SECOND));

    String d2 = "201107101112";
    log.debug(DateUtils.format(DateUtils.parse(d2), DateUtils.SECOND));
  }

  @Test
  public void testGetPeriodDays() {
    Date beginDate = DateUtils.parse("2012-10-01 00:00:00");
    Date endDate = DateUtils.parse("2012-10-02 00:00:00");
    Assert.assertEquals(1, DateUtils.getPeriod(beginDate, endDate).getDays());
  }

  @Test
  public void tsetGetInterval() {
    Date beginDate = DateUtils.parse("2012-10-01");
    Date endDate = DateUtils.parse("2012-10-02");
    Interval interval = DateUtils.getInterval(beginDate, endDate);
    Date targetDate = DateUtils.parse("2012-10-01 00:00:00");
    log.debug("{}", interval.contains(targetDate.getTime()));
    targetDate = DateUtils.parse("2012-10-01 23:59:59");
    log.debug("{}", interval.contains(targetDate.getTime()));
    targetDate = DateUtils.parse("2012-10-02 00:00:00");
    log.debug("{}", interval.contains(targetDate.getTime()));
  }

  @Test
  public void testGetDuration() {
    Date beginDate = DateUtils.parse("2012-10-01");
    Date endDate = DateUtils.parse("2012-10-02");
    Duration duration = DateUtils.getDuration(beginDate, endDate);
    log.debug("{}", duration.getStandardMinutes());
    duration = DateUtils.getDuration(endDate, beginDate);
    log.debug("{}", duration.getStandardMinutes());
  }
}

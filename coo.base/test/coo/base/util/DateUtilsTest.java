package coo.base.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtilsTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testGetToday() {
		Date today = DateUtils.getToday();
		log.debug(DateUtils.format(today, DateUtils.TO_SECOND));

		String date1 = "2011-7-10";
		log.debug(DateUtils.parse(date1).toString());
	}

	@Test
	public void testGetPeriodDays() {
		Date beginDate = DateUtils.parse("2012-10-01 23:59:59");
		Date endDate = DateUtils.parse("2012-10-02 00:00:00");
		Assert.assertEquals(1, DateUtils.getPeriod(beginDate, endDate)
				.getDays());
	}
}

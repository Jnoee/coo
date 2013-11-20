package coo.base.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionUtilsTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testSortMapByValue() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("D", 4);
		log.debug(map.toString());

		map = CollectionUtils.sortMapByValue(map, false);
		log.debug(map.toString());

		map = CollectionUtils.sortMapByKey(map, true);
		log.debug(map.toString());
	}
}

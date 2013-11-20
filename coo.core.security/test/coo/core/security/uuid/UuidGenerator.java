package coo.core.security.uuid;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UuidGenerator {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			log.debug(UUID.randomUUID().toString());
		}
	}
}

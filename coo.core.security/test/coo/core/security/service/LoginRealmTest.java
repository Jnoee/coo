package coo.core.security.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRealmTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() throws Exception {
		LoginRealm loginRealm = new LoginRealm();
		loginRealm.init();
		log.debug(loginRealm.encryptPassword("admin"));
	}
}
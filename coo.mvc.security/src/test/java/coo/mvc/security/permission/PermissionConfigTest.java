package coo.mvc.security.permission;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import coo.core.config.CoreConfiguration;
import coo.mvc.config.MvcConfiguration;
import coo.mvc.security.config.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CoreConfiguration.class, MvcConfiguration.class,
    SecurityConfiguration.class})
public class PermissionConfigTest {
  private final Logger log = LoggerFactory.getLogger(getClass());
  @Resource
  private PermissionConfig permissionConfig;

  @Test
  public void test() throws Exception {
    log.debug("{}", permissionConfig.getPermission("ADMIN"));
  }
}

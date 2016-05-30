package coo.core.util;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import coo.base.model.Page;

public class JacksonTest {
  private Logger log = LoggerFactory.getLogger(getClass());

  @Test
  public void testMixin() throws Exception {
    Page<String> page = new Page<String>(100, 1, 10);
    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixIn(Page.class, Mixin.class);
    log.debug(mapper.writeValueAsString(page));
  }

  abstract class Mixin {
    @JsonIgnore
    abstract Boolean getFirst();

    @JsonIgnore
    abstract Boolean getLast();

    @JsonIgnore
    abstract List<Integer> getIndexs();
  }
}

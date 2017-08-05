package coo.core.message;

import java.util.List;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import coo.base.constants.Encoding;
import coo.base.exception.BusinessException;
import coo.core.util.SpringUtils;

/**
 * 该类定义了信息配置组件。<br>
 * 信息配置文件指定放置在/META-INF/coo目录下，名称以messages结尾，采用标准的Properties XML文件格式。
 */
@Component
public class MessageSource extends ReloadableResourceBundleMessageSource {
  private static final String MESSAGE_DIR = "/META-INF/coo/";
  private static final String MESSAGE_PATH = "classpath*:" + MESSAGE_DIR + "*messages.xml";

  /**
   * 构造方法。
   */
  public MessageSource() {
    setDefaultEncoding(Encoding.UTF_8);
    setUseCodeAsDefaultMessage(true);
    setBasenames(MESSAGE_PATH);
  }

  /**
   * 获取指定编码的信息。
   * 
   * @param code 信息编码
   * @param vars 信息变量
   * @return 返回指定编码的信息。
   */
  public String get(String code, Object... vars) {
    return getMessage(code, vars, null);
  }

  /**
   * 抛出业务异常。
   * 
   * @param code 信息编码
   * @param vars 信息变量
   */
  public void thrown(String code, Object... vars) {
    throw new BusinessException(get(code, vars));
  }

  /**
   * 抛出业务异常。
   * 
   * @param ex 异常对象
   * @param code 信息编码
   * @param vars 信息变量
   */
  public void thrown(Throwable ex, String code, Object... vars) {
    throw new BusinessException(get(code, vars), ex);
  }

  @Override
  public void setBasenames(String... basenames) {
    List<String> resourceBasenames =
        SpringUtils.getResourceBasenamesByWildcard(MESSAGE_DIR, basenames);
    for (String resourceBasename : resourceBasenames) {
      super.addBasenames(resourceBasename);
      logger.debug("加载配置信息文件[" + resourceBasename + "]成功。");
    }
  }
}

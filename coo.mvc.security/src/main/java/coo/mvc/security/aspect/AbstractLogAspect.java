package coo.mvc.security.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import coo.base.util.CollectionUtils;
import coo.core.message.MessageSource;
import coo.core.util.AspectUtils;
import coo.mvc.security.entity.BnLogEntity;
import coo.mvc.security.service.AbstractBnLogger;

/**
 * 日志切面基类。
 */
public abstract class AbstractLogAspect {
  @Resource
  private AbstractBnLogger<? extends BnLogEntity> bnLogger;
  @Resource
  private MessageSource messageSource;

  /**
   * 生成新的业务日志。
   * 
   * @return 返回业务日志。
   */
  protected BnLogEntity newBnLog() {
    return bnLogger.newBnLog();
  }

  /**
   * 保存业务日志。
   * 
   * @param bnLog 业务日志
   */
  protected void saveBnLog(BnLogEntity bnLog) {
    bnLogger.log(bnLog);
  }

  /**
   * 获取日志信息。
   * 
   * @param code 日志信息编码
   * @param varFieldNames 变量字段名称
   * @param params 方法参数
   * @return 返回日志信息。
   */
  protected String getMessage(String code, String[] varFieldNames, Map<String, Object> params) {
    List<Object> vars = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(varFieldNames)) {
      for (String fieldName : varFieldNames) {
        vars.add(AspectUtils.getParam(fieldName, params));
      }
    }
    return messageSource.get(code, vars.toArray());
  }
}

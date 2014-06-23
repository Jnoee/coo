package coo.core.security.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import coo.base.util.CollectionUtils;
import coo.core.message.MessageSource;
import coo.core.security.entity.BnLogEntity;
import coo.core.security.service.AbstractBnLogger;
import coo.core.util.AspectUtils;
import coo.core.util.SpringUtils;

/**
 * 日志切面基类。
 */
public abstract class AbstractLogAspect {
	/**
	 * 生成新的业务日志。
	 * 
	 * @return 返回业务日志。
	 */
	protected BnLogEntity newBnLog() {
		AbstractBnLogger<? extends BnLogEntity> bnLogger = SpringUtils
				.getBean("bnLogger");
		return bnLogger.newBnLog();
	}

	/**
	 * 保存业务日志。
	 * 
	 * @param bnLog
	 *            业务日志
	 */
	protected void saveBnLog(BnLogEntity bnLog) {
		AbstractBnLogger<? extends BnLogEntity> bnLogger = SpringUtils
				.getBean("bnLogger");
		bnLogger.log(bnLog);
	}

	/**
	 * 获取日志信息。
	 * 
	 * @param code
	 *            日志信息编码
	 * @param varFieldNames
	 *            变量字段名称
	 * @param params
	 *            方法参数
	 * @return 返回日志信息。
	 */
	protected String getMessage(String code, String[] varFieldNames,
			Map<String, Object> params) {
		List<Object> vars = new ArrayList<Object>();
		if (CollectionUtils.isNotEmpty(varFieldNames)) {
			for (String fieldName : varFieldNames) {
				vars.add(AspectUtils.getParam(fieldName, params));
			}
		}
		MessageSource messageSource = SpringUtils.getBean("messageSource");
		return messageSource.get(code, vars.toArray());
	}
}
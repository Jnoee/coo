package coo.mvc.security.blank.service;

import org.springframework.stereotype.Service;

import coo.core.security.service.AbstractBnLogger;
import coo.mvc.security.blank.entity.BnLog;

/**
 * 业务日志组件。
 */
@Service
public class BnLogger extends AbstractBnLogger<BnLog> {
	@Override
	public BnLog newBnLog() {
		return new BnLog();
	}
}
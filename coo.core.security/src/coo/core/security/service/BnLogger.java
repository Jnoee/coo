package coo.core.security.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.base.model.Page;
import coo.core.hibernate.dao.Dao;
import coo.core.hibernate.search.FullTextCriteria;
import coo.core.model.SearchModel;
import coo.core.security.entity.BnLog;
import coo.core.security.entity.UserEntity;
import coo.core.util.SpringUtils;

/**
 * 业务日志组件。
 */
@Service
public class BnLogger {
	@Resource
	private Dao<BnLog> bnLogDao;

	/**
	 * 记录普通日志。
	 * 
	 * @param message
	 *            日志信息
	 */
	@Transactional
	public void log(String message) {
		log(getCurrentUsername(), message);
	}

	/**
	 * 记录普通日志，登录时用。
	 * 
	 * @param username
	 *            操作人的用户名
	 * @param message
	 *            日志信息
	 */
	@Transactional
	public void log(String username, String message) {
		BnLog bnLog = new BnLog(message);
		bnLog.setCreator(username);
		bnLog.setCreateDate(new Date());
		bnLogDao.save(bnLog);
	}

	/**
	 * 记录高级日志。
	 * 
	 * @param bnLog
	 *            日志对象
	 */
	@Transactional
	public void log(BnLog bnLog) {
		bnLog.setCreator(getCurrentUsername());
		bnLog.setCreateDate(new Date());
		bnLogDao.save(bnLog);
	}

	/**
	 * 分页全文搜索日志记录。
	 * 
	 * @param searchModel
	 *            搜索条件
	 * @return 返回符合条件的日志分页对象。
	 */
	@Transactional(readOnly = true)
	public Page<BnLog> searchLog(SearchModel searchModel) {
		FullTextCriteria criteria = bnLogDao.createFullTextCriteria();
		criteria.addSortDesc("createDate", SortField.LONG);
		criteria.setKeyword(searchModel.getKeyword());
		return bnLogDao.searchPage(criteria, searchModel.getPageNo(),
				searchModel.getPageSize());
	}

	/**
	 * 获取指定ID的日志记录。
	 * 
	 * @param logId
	 *            日志ID
	 * @return 返回指定ID的日志记录。
	 */
	@Transactional(readOnly = true)
	public BnLog getLog(String logId) {
		return bnLogDao.get(logId);
	}

	/**
	 * 获取当前登录用户的用户名。
	 * 
	 * @return 返回当前登录用户的用户名。
	 */
	@SuppressWarnings("unchecked")
	private String getCurrentUsername() {
		AbstractSecurityService<?, ? extends UserEntity<?, ?, ?>, ?, ?, ?> securityService = (AbstractSecurityService<?, ? extends UserEntity<?, ?, ?>, ?, ?, ?>) SpringUtils
				.getBean("securityService");
		return securityService.getCurrentUser().getUsername();
	}
}

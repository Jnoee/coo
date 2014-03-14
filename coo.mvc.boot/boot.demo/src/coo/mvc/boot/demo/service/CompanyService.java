package coo.mvc.boot.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.base.util.BeanUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.message.MessageSource;
import coo.core.security.annotations.Log;
import coo.core.security.annotations.Log.LogType;
import coo.core.security.service.BnLogger;
import coo.mvc.boot.demo.entity.Company;

/**
 * 公司管理。
 */
@Service
public class CompanyService {
	@Resource
	private Dao<Company> companyDao;
	@Resource
	private MessageSource messageSource;
	@Resource
	private BnLogger bnLogger;

	/**
	 * 获取所有公司列表。
	 * 
	 * @return 返回所有公司列表。
	 */
	@Transactional(readOnly = true)
	public List<Company> getAllCompany() {
		return companyDao.getAll("name", true);
	}

	/**
	 * 获取公司。
	 * 
	 * @param companyId
	 *            公司ID
	 * @return 返回公司。
	 */
	@Transactional(readOnly = true)
	public Company getCompany(String companyId) {
		return companyDao.get(companyId);
	}

	/**
	 * 新增公司。
	 * 
	 * @param company
	 *            公司
	 */
	@Transactional
	@Log(code = "company.add.log", vars = "name", type = LogType.NEW)
	public void createCompany(Company company) {
		if (!companyDao.isUnique(company, "name")) {
			messageSource.thrown("company.name.exist");
		}
		companyDao.save(company);
	}

	/**
	 * 更新公司。
	 * 
	 * @param company
	 *            公司
	 */
	@Transactional
	@Log(code = "company.edit.log", vars = "name", type = LogType.ALL)
	public void updateCompany(Company company) {
		if (!companyDao.isUnique(company, "name")) {
			messageSource.thrown("company.name.exist");
		}
		Company origCompany = companyDao.get(company.getId());
		BeanUtils.copyFields(company, origCompany);
	}

	/**
	 * 删除公司。
	 * 
	 * @param companyId
	 *            公司ID
	 */
	@Transactional
	@Log(code = "company.delete.success")
	public void deleteCompany(String companyId) {
		companyDao.remove(companyId);
		bnLogger.log(messageSource.get("company.delete.success"));
	}
}

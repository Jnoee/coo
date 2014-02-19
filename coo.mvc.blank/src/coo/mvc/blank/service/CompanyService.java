package coo.mvc.blank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.base.exception.UncheckedException;
import coo.base.util.BeanUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.message.MessageConfig;
import coo.mvc.blank.entity.Company;

/**
 * 公司管理。
 */
@Service
public class CompanyService {
	@Resource
	private Dao<Company> companyDao;
	@Resource
	private MessageConfig messageConfig;

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
	public void createCompany(Company company) {
		if (!companyDao.isUnique(company, "name")) {
			throw new UncheckedException(
					messageConfig.getString("company.name.exists"));
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
	public void updateCompany(Company company) {
		if (!companyDao.isUnique(company, "name")) {
			throw new UncheckedException(
					messageConfig.getString("company.name.exists"));
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
	public void deleteCompany(String companyId) {
		companyDao.remove(companyId);
	}
}

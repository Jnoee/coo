package coo.mvc.blank.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.base.model.Page;
import coo.base.util.BeanUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.hibernate.search.FullTextCriteria;
import coo.core.model.SearchModel;
import coo.mvc.blank.entity.Employee;

/**
 * 职员管理。
 */
@Service
public class EmployeeService {
	@Resource
	private Dao<Employee> employeeDao;

	/**
	 * 分页搜索职员。
	 * 
	 * @param searchModel
	 *            搜索条件
	 * @return 返回符合条件的职员分页对象。
	 */
	@Transactional(readOnly = true)
	public Page<Employee> searchEmployee(SearchModel searchModel) {
		FullTextCriteria criteria = employeeDao.createFullTextCriteria();
		criteria.setKeyword(searchModel.getKeyword());
		return employeeDao.searchPage(criteria, searchModel.getPageNo(),
				searchModel.getPageSize());
	}

	/**
	 * 获取职员。
	 * 
	 * @param employeeId
	 *            职员ID
	 * @return 返回指定ID的职员。
	 */
	@Transactional(readOnly = true)
	public Employee getEmployee(String employeeId) {
		return employeeDao.get(employeeId);
	}

	/**
	 * 新增职员。
	 * 
	 * @param employee
	 *            职员
	 */
	@Transactional
	public void createEmployee(Employee employee) {
		employeeDao.save(employee);
	}

	/**
	 * 更新职员。
	 * 
	 * @param employee
	 *            职员
	 */
	@Transactional
	public void updateEmployee(Employee employee) {
		Employee origEmployee = employeeDao.get(employee.getId());
		BeanUtils.copyFields(employee, origEmployee);
	}

	/**
	 * 删除职员。
	 * 
	 * @param employeeId
	 *            职员ID
	 */
	@Transactional
	public void deleteEmployee(String employeeId) {
		employeeDao.remove(employeeId);
	}
}

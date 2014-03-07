package coo.struts.blank.actions.company;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import coo.base.model.Page;
import coo.core.model.SearchModel;
import coo.struts.actions.GenericAction;
import coo.struts.blank.entity.Company;
import coo.struts.blank.entity.Employee;
import coo.struts.blank.service.CompanyService;
import coo.struts.blank.service.EmployeeService;
import coo.struts.util.AjaxResultUtils;

/**
 * 职员管理。
 */
public class EmployeeAction extends GenericAction implements
		ModelDriven<SearchModel> {
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CompanyService companyService;
	private SearchModel searchModel = new SearchModel();
	private Page<Employee> employeePage;
	private List<Company> companys;
	private Employee employee;

	/**
	 * 查看职员列表。
	 * 
	 * @return 返回职员列表页面。
	 */
	@Action("employee-list")
	public String list() {
		employeePage = employeeService.searchEmployee(searchModel);
		return SUCCESS;
	}

	/**
	 * 新增职员。
	 * 
	 * @return 返回新增职员页面。
	 */
	@Action("employee-add")
	public String add() {
		employee = new Employee();
		companys = companyService.getAllCompany();
		return SUCCESS;
	}

	/**
	 * 保存职员。
	 * 
	 * @return 返回提示信息。
	 */
	@Action("employee-save")
	public String save() {
		employeeService.createEmployee(employee);
		return AjaxResultUtils.close(
				messageSource.get("employee.add.success"),
				"employee-list");
	}

	/**
	 * 编辑职员。
	 * 
	 * @return 返回编辑职员页面。
	 */
	@Action("employee-edit")
	public String edit() {
		String employeeId = request.getParameter("employeeId");
		employee = employeeService.getEmployee(employeeId);
		companys = companyService.getAllCompany();
		return SUCCESS;
	}

	/**
	 * 更新职员。
	 * 
	 * @return 返回提示信息。
	 */
	@Action("employee-update")
	public String update() {
		employeeService.updateEmployee(employee);
		return AjaxResultUtils.close(
				messageSource.get("employee.edit.success"),
				"employee-list");
	}

	/**
	 * 删除职员。
	 * 
	 * @return 返回提示信息。
	 */
	@Action("employee-delete")
	public String delete() {
		String employeeId = request.getParameter("employeeId");
		employeeService.deleteEmployee(employeeId);
		return AjaxResultUtils.refresh(
				messageSource.get("employee.delete.success"),
				"employee-list");
	}

	@Override
	public SearchModel getModel() {
		return searchModel;
	}

	public Page<Employee> getEmployeePage() {
		return employeePage;
	}

	public void setEmployeePage(Page<Employee> employeePage) {
		this.employeePage = employeePage;
	}

	public List<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}

package coo.mvc.boot.demo.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageSource;
import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.mvc.boot.demo.entity.Employee;
import coo.mvc.boot.demo.service.CompanyService;
import coo.mvc.boot.demo.service.EmployeeService;
import coo.mvc.util.DialogResultUtils;
import coo.mvc.util.NavTabResultUtils;

/**
 * 职员管理。
 */
@Controller
@RequestMapping("/company")
@Auth("EMPLOYEE_MANAGE")
public class EmployeeAction {
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CompanyService companyService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看职员列表。
	 * 
	 * @param model
	 *            数据模型
	 * @param searchModel
	 *            搜索条件
	 */
	@RequestMapping("employee-list")
	public void list(Model model, SearchModel searchModel) {
		model.addAttribute("employeePage",
				employeeService.searchEmployee(searchModel));
	}

	/**
	 * 新增职员。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("employee-add")
	public void add(Model model) {
		model.addAttribute(new Employee());
		model.addAttribute("companys", companyService.getAllCompany());
	}

	/**
	 * 保存职员。
	 * 
	 * @param employee
	 *            职员
	 * 
	 * @return 返回提示信息。
	 */
	@RequestMapping("employee-save")
	public ModelAndView save(Employee employee) {
		employeeService.createEmployee(employee);
		return DialogResultUtils.closeAndReloadNavTab(messageSource
				.get("employee.add.success"));
	}

	/**
	 * 编辑职员。
	 * 
	 * @param employeeId
	 *            职员ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("employee-edit")
	public void edit(String employeeId, Model model) {
		model.addAttribute(employeeService.getEmployee(employeeId));
		model.addAttribute("companys", companyService.getAllCompany());
	}

	/**
	 * 更新职员。
	 * 
	 * @param employee
	 *            职员
	 * 
	 * @return 返回提示信息。
	 */
	@RequestMapping("employee-update")
	public ModelAndView update(Employee employee) {
		employeeService.updateEmployee(employee);
		return DialogResultUtils.closeAndReloadNavTab(messageSource
				.get("employee.edit.success"));
	}

	/**
	 * 删除职员。
	 * 
	 * @param employeeId
	 *            职员ID
	 * 
	 * @return 返回提示信息。
	 */
	@RequestMapping("employee-delete")
	public ModelAndView delete(String employeeId) {
		employeeService.deleteEmployee(employeeId);
		return NavTabResultUtils.reload(messageSource
				.get("employee.delete.success"));
	}
}

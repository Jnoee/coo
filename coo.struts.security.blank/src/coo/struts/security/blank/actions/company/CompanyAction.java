package coo.struts.security.blank.actions.company;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import coo.core.security.annotations.Auth;
import coo.struts.actions.GenericAction;
import coo.struts.security.blank.entity.Company;
import coo.struts.security.blank.service.CompanyService;
import coo.struts.util.AjaxResultUtils;

/**
 * 公司管理。
 */
@Auth("COMPANY_MANAGE")
public class CompanyAction extends GenericAction {
	@Resource
	private CompanyService companyService;
	private List<Company> companys;
	private Company company;

	/**
	 * 查看公司列表。
	 * 
	 * @return 返回公司列表页面。
	 */
	@Action("company-list")
	public String list() {
		companys = companyService.getAllCompany();
		return SUCCESS;
	}

	/**
	 * 新增公司。
	 * 
	 * @return 返回新增公司页面。
	 */
	@Action("company-add")
	public String add() {
		company = new Company();
		return SUCCESS;
	}

	/**
	 * 保存公司。
	 * 
	 * @return 返回提示信息。
	 */
	@Action("company-save")
	public String save() {
		companyService.createCompany(company);
		return AjaxResultUtils.close(
				messageSource.get("company.add.success"), "company-list");
	}

	/**
	 * 编辑公司。
	 * 
	 * @return 返回编辑公司页面。
	 */
	@Action("company-edit")
	public String edit() {
		String companyId = request.getParameter("companyId");
		company = companyService.getCompany(companyId);
		return SUCCESS;
	}

	/**
	 * 更新公司。
	 * 
	 * @return 返回提示信息。
	 */
	@Action("company-update")
	public String update() {
		companyService.updateCompany(company);
		return AjaxResultUtils
				.close(messageSource.get("company.edit.success"),
						"company-list");
	}

	/**
	 * 删除公司。
	 * 
	 * @return 返回提示信息。
	 */
	@Action("company-delete")
	public String delete() {
		String companyId = request.getParameter("companyId");
		companyService.deleteCompany(companyId);
		return AjaxResultUtils.refresh(
				messageSource.get("company.delete.success"),
				"company-list");
	}

	public List<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
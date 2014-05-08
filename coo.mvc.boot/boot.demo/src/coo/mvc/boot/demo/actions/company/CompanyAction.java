package coo.mvc.boot.demo.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.mvc.boot.demo.entity.Company;
import coo.mvc.boot.demo.service.CompanyService;
import coo.mvc.util.DialogResultUtils;
import coo.mvc.util.NavTabResultUtils;

/**
 * 公司管理。
 */
@Controller
@RequestMapping("/company")
@Auth("COMPANY_MANAGE")
public class CompanyAction {
	@Resource
	private CompanyService companyService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看公司列表。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("company-list")
	public void list(Model model) {
		model.addAttribute("companys", companyService.getAllCompany());
	}

	/**
	 * 新增公司。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("company-add")
	public void add(Model model) {
		model.addAttribute(new Company());
	}

	/**
	 * 保存公司。
	 * 
	 * @param company
	 *            公司
	 * @return 返回提示信息。
	 */
	@RequestMapping("company-save")
	public ModelAndView save(Company company) {
		companyService.createCompany(company);
		return DialogResultUtils.closeAndReloadNavTab(messageSource
				.get("company.add.success"));
	}

	/**
	 * 编辑公司。
	 * 
	 * @param companyId
	 *            公司ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("company-edit")
	public void edit(String companyId, Model model) {
		model.addAttribute(companyService.getCompany(companyId));
	}

	/**
	 * 更新公司。
	 * 
	 * @param company
	 *            公司
	 * @return 返回提示信息。
	 */
	@RequestMapping("company-update")
	public ModelAndView update(Company company) {
		companyService.updateCompany(company);
		return DialogResultUtils.closeAndReloadNavTab(messageSource
				.get("company.edit.success"));
	}

	/**
	 * 删除公司。
	 * 
	 * @param companyId
	 *            公司ID
	 * @return 返回提示信息。
	 */
	@RequestMapping("company-delete")
	public ModelAndView delete(String companyId) {
		companyService.deleteCompany(companyId);
		return NavTabResultUtils.reload(messageSource
				.get("company.delete.success"));
	}
}
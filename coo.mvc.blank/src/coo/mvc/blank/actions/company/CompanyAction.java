package coo.mvc.blank.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageConfig;
import coo.mvc.blank.entity.Company;
import coo.mvc.blank.service.CompanyService;
import coo.mvc.util.DwzResultUtils;

@Controller
@RequestMapping("/company")
public class CompanyAction {
	@Resource
	private CompanyService companyService;
	@Resource
	private MessageConfig messageConfig;

	@RequestMapping("company-list")
	public void list(Model model) {
		model.addAttribute("companys", companyService.getAllCompany());
	}

	@RequestMapping("company-add")
	public void add(Model model) {
		model.addAttribute(new Company());
	}

	@RequestMapping("company-save")
	public ModelAndView save(Company company) {
		companyService.createCompany(company);
		return DwzResultUtils.close(
				messageConfig.getString("company.add.success"), "company-list");
	}

	@RequestMapping("company-edit")
	public void edit(String companyId, Model model) {
		model.addAttribute(companyService.getCompany(companyId));
	}

	@RequestMapping("company-update")
	public ModelAndView update(Company company) {
		companyService.updateCompany(company);
		return DwzResultUtils
				.close(messageConfig.getString("company.edit.success"),
						"company-list");
	}

	@RequestMapping("company-delete")
	public ModelAndView delete(String companyId) {
		companyService.deleteCompany(companyId);
		return DwzResultUtils.refresh(
				messageConfig.getString("company.delete.success"),
				"company-list");
	}
}

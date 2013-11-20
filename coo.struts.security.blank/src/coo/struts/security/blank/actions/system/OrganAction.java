package coo.struts.security.blank.actions.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.struts.actions.GenericAction;
import coo.struts.security.blank.entity.Organ;
import coo.struts.security.blank.service.SecurityService;
import coo.struts.util.AjaxResultUtils;

/**
 * 机构管理。
 */
@Auth(AdminPermission.CODE)
public class OrganAction extends GenericAction {
	@Resource
	private SecurityService securityService;
	private Organ rootOrgan;
	private List<Organ> parentOrgans = new ArrayList<Organ>();
	private Organ organ;

	/**
	 * 查看机构树。
	 * 
	 * @return 返回机构树页面。
	 */
	@Action("organ-list")
	public String list() {
		rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		return SUCCESS;
	}

	/**
	 * 新增机构。
	 * 
	 * @return 返回新增机构页面。
	 */
	@Action("organ-add")
	public String add() {
		rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		organ = new Organ();
		organ.setParent(rootOrgan);
		return SUCCESS;
	}

	/**
	 * 保存机构。
	 * 
	 * @return 返回保存机构成功提示。
	 */
	@Action("organ-save")
	public String save() {
		securityService.createOrgan(organ);
		return AjaxResultUtils.close(getMessage("organ.add.success"),
				"organ-list");
	}

	/**
	 * 编辑机构。
	 * 
	 * @return 返回编辑机构页面。
	 */
	@Action("organ-edit")
	public String edit() {
		rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		String organId = request.getParameter("organId");
		organ = securityService.getOrgan(organId);
		parentOrgans = rootOrgan.getOrganTree();
		parentOrgans.remove(organ);
		return SUCCESS;
	}

	/**
	 * 更新机构。
	 * 
	 * @return 返回更新机构成功提示。
	 */
	@Action("organ-update")
	public String update() {
		securityService.updateOrgan(organ);
		return AjaxResultUtils.refresh(getMessage("organ.edit.success"),
				"organ-list");
	}

	public Organ getRootOrgan() {
		return rootOrgan;
	}

	public void setRootOrgan(Organ rootOrgan) {
		this.rootOrgan = rootOrgan;
	}

	public List<Organ> getParentOrgans() {
		return parentOrgans;
	}

	public void setParentOrgans(List<Organ> parentOrgans) {
		this.parentOrgans = parentOrgans;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
}

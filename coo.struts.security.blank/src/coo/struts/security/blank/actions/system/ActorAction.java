package coo.struts.security.blank.actions.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.struts.actions.GenericAction;
import coo.struts.security.blank.entity.Actor;
import coo.struts.security.blank.entity.Organ;
import coo.struts.security.blank.entity.Role;
import coo.struts.security.blank.entity.User;
import coo.struts.security.blank.service.SecurityService;
import coo.struts.util.AjaxResultUtils;

/**
 * 职务管理。
 */
@Auth(AdminPermission.CODE)
public class ActorAction extends GenericAction {
	@Resource
	private SecurityService securityService;
	private Actor actor;
	private Organ rootOrgan;
	private List<Role> roles = new ArrayList<Role>();

	/**
	 * 新增职务。
	 * 
	 * @return 返回新增职务页面。
	 */
	@Action("actor-add")
	public String add() {
		String userId = request.getParameter("userId");
		User user = new User();
		user.setId(userId);
		actor = new Actor();
		actor.setUser(user);
		rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		roles = securityService.getAllRole();
		return SUCCESS;
	}

	/**
	 * 保存职务。
	 * 
	 * @return 返回保存职务成功提示。
	 */
	@Action("actor-save")
	public String save() {
		securityService.createActor(actor);
		return AjaxResultUtils.close(getMessage("actor.add.success"),
				"user-edit");
	}

	/**
	 * 编辑职务。
	 * 
	 * @return 返回编辑职务页面。
	 */
	@Action("actor-edit")
	public String edit() {
		String actorId = request.getParameter("actorId");
		actor = securityService.getActor(actorId);
		rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		roles = securityService.getAllRole();
		return SUCCESS;
	}

	/**
	 * 更新职务。
	 * 
	 * @return 返回更新职务成功提示。
	 */
	@Action("actor-update")
	public String update() {
		securityService.updateActor(actor);
		return AjaxResultUtils.close(getMessage("actor.edit.success"),
				"user-edit");
	}

	/**
	 * 删除职务。
	 * 
	 * @return 返回删除职务成功提示。
	 */
	@Action("actor-delete")
	public String delete() {
		String actorId = request.getParameter("actorId");
		securityService.deleteActor(actorId);
		return AjaxResultUtils.refresh(getMessage("actor.delete.success"),
				"user-edit");
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Organ getRootOrgan() {
		return rootOrgan;
	}

	public void setRootOrgan(Organ rootOrgan) {
		this.rootOrgan = rootOrgan;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}

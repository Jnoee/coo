package coo.struts.security.blank.actions.system;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import coo.core.security.annotations.Auth;
import coo.core.security.model.PwdChangeModel;
import coo.struts.actions.GenericAction;
import coo.struts.security.blank.service.SecurityService;
import coo.struts.util.AjaxResultUtils;

/**
 * 个人管理。
 */
@Auth
public class PersonAction extends GenericAction {
	@Resource
	private SecurityService securityService;
	private PwdChangeModel pwdChangeModel;

	/**
	 * 修改个人密码。
	 * 
	 * @return 返回修改个人密码页面。
	 */
	@Action("person-pwd-change")
	public String pwdChange() {
		pwdChangeModel = new PwdChangeModel();
		return SUCCESS;
	}

	/**
	 * 保存个人密码。
	 * 
	 * @return 返回保存个人密码成功提示。
	 */
	@Action("person-pwd-change-save")
	public String pwdChangeSave() {
		securityService.changePassword(pwdChangeModel.getOldPwd(),
				pwdChangeModel.getNewPwd());
		return AjaxResultUtils.close(getMessage("person.pwd.change.success"));
	}

	/**
	 * 切换职务。
	 * 
	 * @return 返回主页。
	 */
	@Action(value = "person-actor-change", results = { @Result(name = "success", type = "redirectAction", params = {
			"actionName", "index", "namespace", "/" }) })
	public String change() {
		String actorId = request.getParameter("actorId");
		securityService.changeActor(actorId);
		return SUCCESS;
	}

	public PwdChangeModel getPwdChangeModel() {
		return pwdChangeModel;
	}

	public void setPwdChangeModel(PwdChangeModel pwdChangeModel) {
		this.pwdChangeModel = pwdChangeModel;
	}
}

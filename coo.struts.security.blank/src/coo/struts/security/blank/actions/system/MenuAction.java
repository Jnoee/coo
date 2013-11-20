package coo.struts.security.blank.actions.system;

import com.opensymphony.xwork2.ActionSupport;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;

/**
 * 系统管理菜单。
 */
@Auth(AdminPermission.CODE)
public class MenuAction extends ActionSupport {
}

package coo.core.security.model;

import java.io.Serializable;

/**
 * 用户名/密码登录model。
 */
public class LoginModel implements Serializable {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

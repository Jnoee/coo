package coo.core.security.permission;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 权限。对应权限配置文件中的permission节点。
 */
@XStreamAlias("permission")
public class Permission implements Serializable {
	@XStreamAsAttribute
	private Integer id;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String code;
	@XStreamAsAttribute
	private String depends;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepends() {
		return depends;
	}

	public void setDepends(String depends) {
		this.depends = depends;
	}

	@Override
	public String toString() {
		return id + ":" + code + ":" + name;
	}
}

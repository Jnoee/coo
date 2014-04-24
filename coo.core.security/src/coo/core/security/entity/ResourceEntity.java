package coo.core.security.entity;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coo.base.util.DateUtils;
import coo.base.util.StringUtils;
import coo.core.hibernate.search.DateBridge;
import coo.core.model.UuidEntity;
import coo.core.security.annotations.LogBean;
import coo.core.security.annotations.LogField;
import coo.core.security.service.AbstractSecurityService;
import coo.core.util.SpringUtils;

/**
 * 资源实体基类。
 * 
 * @param <U>
 *            用户类型
 */
@MappedSuperclass
@JsonIgnoreProperties({ "creator", "createDate", "modifier", "modifyDate" })
public abstract class ResourceEntity<U extends UserEntity<U, ?, ?>> extends
		UuidEntity {
	@LogBean(@LogField(text = "创建人", property = "username"))
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatorId")
	@NotNull
	protected U creator;
	@LogField(text = "创建时间", format = DateUtils.SECOND)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Field(analyze = Analyze.NO)
	@FieldBridge(impl = DateBridge.class)
	protected Date createDate;
	@LogBean(@LogField(text = "修改人", property = "username"))
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modifierId")
	@NotNull
	protected U modifier;
	@LogField(text = "修改时间", format = DateUtils.SECOND)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Field(analyze = Analyze.NO)
	@FieldBridge(impl = DateBridge.class)
	protected Date modifyDate;

	/**
	 * 根据上下文自动填充自身属性。
	 */
	public void autoFillIn() {
		if (StringUtils.isEmpty(getId())) {
			creator = getCurrentUser();
			createDate = new Date();
			modifier = getCurrentUser();
			modifyDate = new Date();
		} else {
			modifier = getCurrentUser();
			modifyDate = new Date();
		}
	}

	/**
	 * 从当前上下文中获取用户对象。
	 * 
	 * @return 返回当前上下文中的用户对象。
	 */
	private U getCurrentUser() {
		AbstractSecurityService<?, U, ?, ?, ?> securityService = SpringUtils
				.getBean("securityService");
		return securityService.getCurrentUser();
	}

	public U getCreator() {
		return creator;
	}

	public void setCreator(U creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public U getModifier() {
		return modifier;
	}

	public void setModifier(U modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}

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

import coo.base.util.StringUtils;
import coo.core.hibernate.search.DateBridge;
import coo.core.model.UuidEntity;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatorId")
	@NotNull
	protected U creator;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
	protected Date createDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modifierId")
	@NotNull
	protected U modifier;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
	protected Date modifyDate;

	/**
	 * 自动填充创建人、创建时间、修改人、修改时间。
	 */
	public void autoFillIn() {
		AbstractSecurityService<?, U, ?, ?, ?> securityService = SpringUtils
				.getBean("securityService");
		U operator = securityService.getDefaultOperator();
		if (StringUtils.isEmpty(getId())) {
			setCreator(operator);
			setCreateDate(new Date());
			setModifier(operator);
			setModifyDate(new Date());
		} else {
			setModifier(operator);
			setModifyDate(new Date());
		}
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

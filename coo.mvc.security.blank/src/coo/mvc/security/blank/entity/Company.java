package coo.mvc.security.blank.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.NotBlank;

import coo.core.model.UuidEntity;
import coo.mvc.security.blank.model.CompanyExtendInfo;

/**
 * 公司。
 */
@Entity
@Table(name = "Tmp_Company")
public class Company extends UuidEntity {
	/** 名称 */
	@NotBlank
	@Field(analyze = Analyze.NO)
	private String name;
	/** 成立时间 */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date foundDate;
	/** 是否可用 */
	@NotNull
	private Boolean enabled = true;
	/** 扩展信息 */
	@Type(type = "Json")
	private CompanyExtendInfo extendInfo;
	/** 关联职员 */
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<Employee>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public CompanyExtendInfo getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(CompanyExtendInfo extendInfo) {
		this.extendInfo = extendInfo;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

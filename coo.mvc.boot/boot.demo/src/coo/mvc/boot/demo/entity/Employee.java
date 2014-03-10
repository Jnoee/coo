package coo.mvc.boot.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import coo.core.hibernate.search.ArrayBridge;
import coo.core.hibernate.search.IEnumTextBridge;
import coo.core.security.entity.ResourceEntity;
import coo.mvc.boot.core.entity.User;
import coo.mvc.boot.demo.enums.Sex;

/**
 * 职员。
 */
@Entity
@Table(name = "Tmp_Employee")
@Indexed
public class Employee extends ResourceEntity<User> {
	/** 关联部门 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId")
	@IndexedEmbedded(depth = 1)
	private Company company;
	/** 姓名 */
	@NotBlank
	@Field(analyze = Analyze.NO)
	private String name;
	/** 年龄 */
	@NotNull
	@Range(min = 1, max = 999)
	private Integer age = 18;
	/** 性别 */
	@Type(type = "IEnum")
	@Field(analyze = Analyze.NO)
	@FieldBridge(impl = IEnumTextBridge.class)
	private Sex sex = Sex.MALE;
	/** 兴趣爱好 */
	@Type(type = "Array")
	@Field
	@FieldBridge(impl = ArrayBridge.class)
	private String[] interests = new String[] {};

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String[] getInterests() {
		return interests;
	}

	public void setInterests(String[] interests) {
		this.interests = interests;
	}
}
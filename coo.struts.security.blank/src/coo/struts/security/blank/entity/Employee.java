package coo.struts.security.blank.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import coo.base.util.StringUtils;
import coo.core.hibernate.search.IEnumListTextBridge;
import coo.core.hibernate.search.IEnumTextBridge;
import coo.core.security.entity.ResourceEntity;
import coo.struts.security.blank.enums.Interest;
import coo.struts.security.blank.enums.Sex;

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
	@Field
	private String name;
	/** 年龄 */
	@NotNull
	@Range(min = 1, max = 999)
	private Integer age = 18;
	/** 性别 */
	@Type(type = "IEnum")
	@Field
	@FieldBridge(impl = IEnumTextBridge.class)
	private Sex sex = Sex.MALE;
	/** 兴趣爱好 */
	@Type(type = "IEnumList")
	@Field
	@FieldBridge(impl = IEnumListTextBridge.class)
	private List<Interest> interests = new ArrayList<Interest>();

	/**
	 * 获取兴趣爱好的文本。
	 * 
	 * @return 返回兴趣爱好的文本。
	 */
	public String getInterestTexts() {
		List<String> interestTexts = new ArrayList<String>();
		for (Interest interest : interests) {
			interestTexts.add(interest.getText());
		}
		return StringUtils.join(interestTexts, ",");
	}

	/**
	 * 获取兴趣爱好值列表。
	 * 
	 * @return 返回兴趣爱好值列表。
	 */
	public List<String> getInterestValues() {
		List<String> interestValues = new ArrayList<String>();
		for (Interest interest : interests) {
			interestValues.add(interest.getValue());
		}
		return interestValues;
	}

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

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
}
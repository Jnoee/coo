package coo.core.report.model;

import java.util.Date;

/**
 * 职员。
 */
public class Employee {
	/** 姓名 */
	private String name;
	/** 年龄 */
	private Integer age = 18;
	/** 生日 */
	private Date birthDate;
	/** 基本工资 */
	private Double payment = 0d;
	/** 奖金比例 */
	private Double bonus = 0d;
	/** 上级 */
	private Employee superior;

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Employee getSuperior() {
		return superior;
	}

	public void setSuperior(Employee superior) {
		this.superior = superior;
	}
}
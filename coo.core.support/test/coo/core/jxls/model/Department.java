package coo.core.jxls.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门。
 */
public class Department {
	/** 名称 */
	private String name;
	/** 主管 */
	private Employee chief;
	/** 关联职员列表 */
	private List<Employee> employees = new ArrayList<Employee>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getChief() {
		return chief;
	}

	public void setChief(Employee chief) {
		this.chief = chief;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

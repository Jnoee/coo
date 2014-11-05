package coo.core.report.jxls;

import java.util.ArrayList;
import java.util.List;

import coo.base.util.DateUtils;
import coo.core.report.model.Department;
import coo.core.report.model.Employee;

public abstract class AbstractSample {
	protected String outputDir = "test/META-INF/coo/report";

	protected List<Department> genDepartments(Integer count) {
		List<Department> departments = new ArrayList<Department>();

		for (int i = 0; i < count; i++) {
			Department department = new Department();
			department.setName("部门" + i);

			Employee chief = genEmployees(1, null).get(0);
			chief.setName("部门主管" + i);

			department.setChief(chief);
			department.setEmployees(genEmployees(10, chief));

			departments.add(department);
		}

		return departments;
	}

	protected List<Employee> genEmployees(Integer count, Employee superior) {
		List<Employee> employees = new ArrayList<Employee>();

		for (int i = 0; i < count; i++) {
			Employee employee = new Employee();
			employee.setName("职员" + i);
			employee.setAge(30 + i);
			employee.setBirthDate(DateUtils.parse(1983 - i + "-01-01"));
			employee.setPayment(20000.00 + 1000 * i);
			employee.setBonus(0.25 - i * 0.01);
			employee.setSuperior(superior);
			employees.add(employee);
		}

		return employees;
	}
}

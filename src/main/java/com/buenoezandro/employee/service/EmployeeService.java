package com.buenoezandro.employee.service;

import java.util.List;

import com.buenoezandro.employee.dto.EmployeeDTO;
import com.buenoezandro.employee.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	Employee saveEmployee(EmployeeDTO employeeDTO);

	Employee updateEmployee(Long id, EmployeeDTO employeeDTO);
	
	void deleteEmployee(Long id);

}

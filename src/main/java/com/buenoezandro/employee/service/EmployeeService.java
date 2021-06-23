package com.buenoezandro.employee.service;

import java.util.List;

import com.buenoezandro.employee.dto.EmployeeDTO;
import com.buenoezandro.employee.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(EmployeeDTO employeeDTO);

	Employee getEmployeeById(Long id);

	List<Employee> getAllEmployees();

}

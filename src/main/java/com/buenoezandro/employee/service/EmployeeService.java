package com.buenoezandro.employee.service;

import com.buenoezandro.employee.dto.EmployeeDTO;
import com.buenoezandro.employee.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(EmployeeDTO employeeDTO);

	Employee getEmployeeById(Long id);

}

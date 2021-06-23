package com.buenoezandro.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.employee.dto.EmployeeDTO;
import com.buenoezandro.employee.exception.ResourceNotFoundException;
import com.buenoezandro.employee.model.Employee;
import com.buenoezandro.employee.repository.EmployeeRepository;
import com.buenoezandro.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Employee getEmployeeById(Long id) {
		var employeeId = this.employeeRepository.findById(id);
		return employeeId.orElseThrow(() -> new ResourceNotFoundException(null, null, id));
	}

	@Transactional
	@Override
	public Employee saveEmployee(EmployeeDTO employeeDTO) {
		return this.employeeRepository.save(
				new Employee(null, employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail()));
	}

}

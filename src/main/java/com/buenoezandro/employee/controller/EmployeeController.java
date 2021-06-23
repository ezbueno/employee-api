package com.buenoezandro.employee.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buenoezandro.employee.dto.EmployeeDTO;
import com.buenoezandro.employee.model.Employee;
import com.buenoezandro.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		List<Employee> employees = this.employeeService.getAllEmployees();
		return ResponseEntity.ok().body(employees.stream().map(EmployeeDTO::new).collect(Collectors.toList()));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		var employee = this.employeeService.getEmployeeById(id);
		return ResponseEntity.ok().body(new EmployeeDTO(employee));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		var newEmployee = this.employeeService.saveEmployee(employeeDTO);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmployee.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
		var employee = this.employeeService.updateEmployee(id, employeeDTO);
		return ResponseEntity.ok().body(new EmployeeDTO(employee));
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		this.employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

}

package springboot.cruddemo.service;

import java.util.List;

import springboot.cruddemo.entity.Employee;

public interface EmployeeService {
	List<Employee> findAll();
	
	Employee findById(int Id);
	
	Employee save(Employee theEmployee);
	
	void delete(int Id);
}

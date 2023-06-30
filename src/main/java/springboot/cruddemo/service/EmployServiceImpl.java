package springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.cruddemo.dao.EmployeeRepository;
import springboot.cruddemo.entity.Employee;

@Service
public class EmployServiceImpl implements EmployeeService {
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int Id) {
		Optional<Employee> result = employeeRepository.findById(Id);
		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Didn't find employee: " + Id);
		}
		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		return employeeRepository.save(theEmployee);
	}

	@Override
	public void delete(int Id) {
		employeeRepository.deleteById(Id);
	}

}

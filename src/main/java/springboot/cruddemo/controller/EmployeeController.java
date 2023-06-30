package springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.cruddemo.entity.Employee;
import springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employee")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employee/{theId}")
    public Employee findById(@PathVariable int theId) {
        Employee theEmployee = employeeService.findById(theId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found: " + theId);
        }
        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employee")
    public Employee updateEmploy(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employee/{theId}")
    public String deleteEmploy(@PathVariable int theId) {

        Employee theEmployee = employeeService.findById(theId);

        // throw exception if null
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found: " + theId);
        }
        employeeService.delete(theId);
        return "Deleted employee id: " + theId;
    }
}

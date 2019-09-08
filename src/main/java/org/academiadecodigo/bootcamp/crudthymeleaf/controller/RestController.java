package org.academiadecodigo.bootcamp.crudthymeleaf.controller;

import org.academiadecodigo.bootcamp.crudthymeleaf.entity.Employee;
import org.academiadecodigo.bootcamp.crudthymeleaf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/employees")
public class RestController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<Employee>> listEmployees() {

        List employees = new ArrayList();
        for (Employee employee : employeeService.findAll()) {
            employees.add(employee);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<Employee>showEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.findById(id);
        if(employee.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Employee savedEmployee  = employeeService.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<Employee> editEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult, @PathVariable Integer id) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        /*if(employeeService.findById(id) != null && !employee.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        if(employeeService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee.setId(id);
        Employee editedEmployee = employeeService.save(employee);
        return new ResponseEntity<>(editedEmployee, HttpStatus.OK);
    }
}
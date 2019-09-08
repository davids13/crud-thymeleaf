package org.academiadecodigo.bootcamp.crudthymeleaf.service;

import org.academiadecodigo.bootcamp.crudthymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(Integer id);

    List<Employee> findAll();

    Employee save(Employee employee);

    void deleteById(Integer id);

    List<Employee> findByFirstNameOrLastName(String name, String last);
}
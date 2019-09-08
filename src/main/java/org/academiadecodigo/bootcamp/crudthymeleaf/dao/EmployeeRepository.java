package org.academiadecodigo.bootcamp.crudthymeleaf.dao;

import org.academiadecodigo.bootcamp.crudthymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //no implementation code needed
    //JpaRepository will give us for free all CRUD methods

    List<Employee> findAllByOrderByFirstNameAsc();
    //spring data jpa will parse the method name
    //looks for a specific format and patter creates appropriate query...behind the scenes
    //from Employee order by firstName asc

    List<Employee> findEmployeeByFirstNameOrLastName(String name, String last);
}
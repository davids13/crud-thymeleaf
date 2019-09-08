package org.academiadecodigo.bootcamp.crudthymeleaf.service;

import org.academiadecodigo.bootcamp.crudthymeleaf.dao.EmployeeRepository;
import org.academiadecodigo.bootcamp.crudthymeleaf.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    // @Transactional - we can remove since jpaRepository provides this functionality

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;

        if(result.isPresent()){
            employee = result.get();
        }
        else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByFirstNameOrLastName(String name, String last) {
        if(name == "" && last == "") {
            return employeeRepository.findAllByOrderByFirstNameAsc();
        }
        else {
            return employeeRepository.findEmployeeByFirstNameOrLastName(name.toLowerCase(), last.toLowerCase());
        }
    }
}
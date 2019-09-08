package org.academiadecodigo.bootcamp.crudthymeleaf.controller;

import org.academiadecodigo.bootcamp.crudthymeleaf.entity.Employee;
import org.academiadecodigo.bootcamp.crudthymeleaf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    //TODO apply DTO pattern
    //TODO add phone number
    //TODO register date and add acs/desc
    //TODO add photo

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String home() {

        return "employees/home";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("search") String searchName, Model model) {

        //search customers from the service
        List<Employee> employees = employeeService.findByFirstNameOrLastName(searchName, searchName);

        //add the customer to the model
        model.addAttribute("employees", employees);
        model.addAttribute("size", employees.size());

        return "employees/list";
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("size",employees.size());
        return "employees/list";
    }

    @GetMapping("/showFormForAdd")
    public String addEmployee(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "employees/employee-form";
        }
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {

        model.addAttribute("employee", employeeService.findById(id));
        return "employees/employee-form";
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
package com.example.EmployeeDepartment.controller;

import com.example.EmployeeDepartment.entity.Department;
import com.example.EmployeeDepartment.entity.Employee;
import com.example.EmployeeDepartment.services.DepService;
import com.example.EmployeeDepartment.services.EmpService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.EmployeeDepartment.controller.depController;

import java.util.List;

import static com.example.EmployeeDepartment.controller.depController.departmentid;

@Controller
@RequestMapping("/employees")
public class empController {
    @Autowired
    private EmpService service;
    @Autowired
    private DepService depService;
    @GetMapping("")
    public String getAllEmployees(Model model) {
        List<Employee> employees = service.getEmployees();
        model.addAttribute("emps",employees);
        return "employees-list";
    }
    @PostMapping("/employee")
    public String addEmployee(@ModelAttribute("obj") Employee employee){
        Department dep=depService.getDepById(departmentid).orElse(null);
        employee.setDepartment(dep);
        service.addEmployee(employee);
        return "redirect:/departments/employees?id="+departmentid;
    }

    @GetMapping("/employee")
    public String updateEmployee(@RequestParam(name = "id") int id, Model model)
    {
        Employee emp=service.getEmployeeById(id);
        model.addAttribute("obj",emp);
        return "employee-form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(name = "id") int id){
        service.deleteEmployee(id);
        return "redirect:/departments/employees?id="+departmentid;
    }
    @GetMapping("/showEmployeeForm")
    public String showEmployeeForm(Model model){
        Employee employee=new Employee();
        model.addAttribute("obj",employee);
        return "employee-form";
    }
}

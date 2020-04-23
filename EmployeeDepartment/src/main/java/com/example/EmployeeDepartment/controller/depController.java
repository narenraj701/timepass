package com.example.EmployeeDepartment.controller;

import com.example.EmployeeDepartment.entity.Department;
import com.example.EmployeeDepartment.entity.Employee;
import com.example.EmployeeDepartment.services.DepService;
import com.example.EmployeeDepartment.services.DepServiceImpl;
import com.example.EmployeeDepartment.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;
import java.util.LongSummaryStatistics;

@Controller
@RequestMapping("/departments")
public class depController {
    @Autowired
    private DepService depService;
    public static int departmentid;
    @GetMapping("")
    public String getDepartments(Model model){
        List<Department> list=depService.getDepartments();
        model.addAttribute("deps",list);
        return "departments";
    }
    @PostMapping("/department")
    public String addDepartment(@ModelAttribute("depObj") Department dep){
        depService.addDepartment(dep);
        return "redirect:/departments";
    }
    @GetMapping("/employees")
    public String getEmployees(@RequestParam(name = "id") int id,Model model){
        departmentid=id;
        List<Employee> employeeList=depService.getEmployees(id);
        model.addAttribute("emps",employeeList);
        return "employees-list";
    }
    @GetMapping("/showDepartmentForm")
    public String showDepartmentForm(Model model){
        Department dep=new Department();
        model.addAttribute("depObj",dep);
        return "Department-form";
    }
}

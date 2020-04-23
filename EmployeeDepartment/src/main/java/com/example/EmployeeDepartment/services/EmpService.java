package com.example.EmployeeDepartment.services;

import com.example.EmployeeDepartment.entity.Employee;

import java.util.List;

public interface EmpService {
    public List<Employee> getEmployees();
    public Employee getEmployeeById(int id);
    public Employee addEmployee(Employee employee);
    public Employee editEmployee(Employee employee);
    public void deleteEmployee(int id);
}

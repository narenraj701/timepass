package com.example.EmployeeDepartment.services;

import com.example.EmployeeDepartment.DAO.DepRepository;
import com.example.EmployeeDepartment.DAO.EmpRepository;
import com.example.EmployeeDepartment.entity.Department;
import com.example.EmployeeDepartment.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private DepRepository depRepository;
    @Override
    public List<Employee> getEmployees() {
        return empRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        //get department object if present
        Department dept = depRepository.findById(employee.getDepartment().getId()).orElse(null);
        //create new department if not present
        if (null == dept) {
            dept = new Department();
        }
        dept.setDepName(employee.getDepartment().getDepName());
        //set employee department
        employee.setDepartment(dept);
        //save employee
        Employee emp = empRepository.save(employee);
        //get list of employees for that department
        List<Employee> employeeList=emp.getDepartment().getEmployees();
        if(null==employeeList){
            employeeList=new ArrayList<>();
        }
        employeeList.add(emp);

        return emp;
        }
    public Employee getEmployeeById(int id){
        return empRepository.findById(id).get();
    }
    @Override
    public Employee editEmployee(Employee employee) {
        return empRepository.save(employee);
    }
    public void deleteEmployee(int id){
        if(!empRepository.existsById(id)) {
            empRepository.deleteById(id);
            return;
        }
        Employee emp=getEmployeeById(id);
        Department dep=depRepository.
                findById(emp.getDepartment().getId()).orElse(null);
        if(null!=dep)
            delete(emp,dep.getEmployees());
        empRepository.deleteById(id);
    }

    private void delete(Employee emp, List<Employee> employees) {
        employees.remove(emp);
    }
}

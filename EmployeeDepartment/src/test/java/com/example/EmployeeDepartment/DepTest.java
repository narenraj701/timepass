package com.example.EmployeeDepartment;

import com.example.EmployeeDepartment.DAO.DepRepository;
import com.example.EmployeeDepartment.entity.Department;
import com.example.EmployeeDepartment.entity.Employee;
import com.example.EmployeeDepartment.services.DepService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepTest {
    @Autowired
    private DepService depService;
    @MockBean
    private DepRepository depRepository;
    @Test
    public void getDepartmentsTest(){
        Department dep=new Department("Zemsoso");
        Mockito.when(depRepository.findAll()).thenReturn
                (Stream.of(new Department("Zemoso"),
                        new Department("Microsoft"))
                        .collect(Collectors.toList()));
        Assert.assertEquals(2,depService.getDepartments().size());
    }
    @Test
    public void addDepartmentTest(){
        Department dep=new Department("Zemoso");
        Mockito.when(depRepository.save(dep)).thenReturn(dep);
        Assert.assertEquals(dep,depService.addDepartment(dep));
    }
    @Test
    public void getEmployeesTest(){
        Department dep = new Department(1, "Zemsoso");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add((new Employee(1,"Naren","Raju",dep)));
        dep.setEmployees(employeeList);
        Mockito.when(depRepository.findById(1).get().getEmployees()).thenReturn(employeeList);

    }
    @Test
    public void getDepByIdTest(){
        int id=1;
        Department dep=new Department(1,"Zemsoso");
        Mockito.when(depRepository.findById(id)).thenReturn(Optional.of(dep));
        Assert.assertEquals(1,depService.getDepById(id).get().getId());
    }

}

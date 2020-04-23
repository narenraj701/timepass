package com.example.EmployeeDepartment;

import com.example.EmployeeDepartment.DAO.EmpRepository;
import com.example.EmployeeDepartment.entity.Department;
import com.example.EmployeeDepartment.entity.Employee;
import com.example.EmployeeDepartment.services.EmpService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmpService empService;
    @MockBean
    private EmpRepository empRepository;
    @Test
    public void getEmployeesTest(){
        Department dep=new Department("Zemsoso");
        Mockito.when(empRepository.findAll()).thenReturn
                (Stream.of(new Employee("Naren","Raju",dep),
                            new Employee("Vishal","N",dep))
                        .collect(Collectors.toList()));
        Assert.assertEquals(2,empService.getEmployees().size());

    }

    @Test
    public void getEmployeeByIdTest(){
        int id=1;
        Department dep=new Department("Zemsoso");
        Employee employee=new Employee(1,"Naren","Raju",dep);
        Mockito.when(empRepository.findById(id)).thenReturn(Optional.of((employee)));
        Assert.assertEquals(1,empService.getEmployeeById(id).getId());
    }
    @Test
    public void saveEmpTest(){
        Department dep=new Department("Zemsoso");
        Employee employee=new Employee(5,"Naren","Raju",dep);
        Mockito.when(empRepository.save(employee)).thenReturn(employee);
        Assert.assertEquals(employee,empService.addEmployee(employee));
    }
    @Test
    public void deleteEmpTest(){
        Department dep=new Department("Zemoso");
        Employee employee=new Employee(5,"Naren","Raju",dep);
        empService.deleteEmployee(employee.getId());
        Mockito.verify(empRepository,Mockito.times(1))
                .deleteById(employee.getId());
    }

}

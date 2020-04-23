package com.example.EmployeeDepartment.DAO;

import com.example.EmployeeDepartment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface EmpRepository extends JpaRepository<Employee,Integer> {
}

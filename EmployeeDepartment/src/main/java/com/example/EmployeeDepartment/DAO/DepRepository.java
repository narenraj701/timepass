package com.example.EmployeeDepartment.DAO;

import com.example.EmployeeDepartment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepRepository extends JpaRepository<Department,Integer> {
}

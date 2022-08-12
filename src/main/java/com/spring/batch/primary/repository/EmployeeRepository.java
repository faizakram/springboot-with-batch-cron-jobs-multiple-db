package com.spring.batch.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.batch.primary.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

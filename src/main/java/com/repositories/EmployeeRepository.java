package com.repositories;

import com.pojos.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployeesByPhone(String phone);
    List<Employee> getEmployeesByEmail(String email);
    List<Employee> getEmployees(int page);
}

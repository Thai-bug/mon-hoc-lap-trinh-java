package com.repositories;

import com.pojos.Employee;

import java.util.Set;

public interface EmployeeRepository {
    Set<Employee> getEmployeesByPhone(String phone);

    Employee getEmployeeByEmail(String email);

    Employee loadLoginEmployee();

    Set<Employee> getEmployees(int page, String kw);

    long getCountAllEmployees(String kw);

    Employee getEmployeeById(int id);

    boolean updateEmployeeAvatar(Employee employee);

    boolean updateEmployee(Employee employee);

    Set<Employee> getParentsList();

    boolean checkChildInParent(int childId);

    boolean createEmployee(Employee employee);
}

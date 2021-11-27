package com.repositories;

import com.pojos.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployeesByPhone(String phone);

    Employee getEmployeeByEmail(String email);

    Employee loadLoginEmployee();

    List<Employee> getEmployees(int page, String kw);

    long getCountAllEmployees(String kw);

    Employee getEmployeeById(int id);

    boolean updateEmployeeAvatar(Employee employee);

    boolean updateEmployee(Employee employee);

    List<Employee> getParentsList();

    boolean checkChildInParent(int childId);
}

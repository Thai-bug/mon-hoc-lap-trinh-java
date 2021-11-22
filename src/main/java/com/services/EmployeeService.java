package com.services;

import com.pojos.Employee;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {
    Employee login(String phoneNumber, String password);

    boolean addUser(Employee employee);

    List<Employee> getEmployeeIn(String phoneNumber);

    List<Employee> getEmployees(int page, String kw);

    long getCountAllEmployee(String kw);

    Employee getEmployeeDetail(int id);

    boolean updateEmployeeAvatar(Employee employee);
}

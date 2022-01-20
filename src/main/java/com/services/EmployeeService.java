package com.services;

import com.pojos.Employee;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface EmployeeService extends UserDetailsService {
    Employee login(String phoneNumber, String password);

    boolean addUser(Employee employee);

    Employee getEmployeeIn(String email);

    Set<Employee> getEmployees( String kw, int page, int length);

    long getCountAllEmployee(String kw);

    Employee getEmployeeDetail(int id);

    boolean updateEmployeeAvatar(Employee employee);

    boolean updateEmployee(Employee employee);

    Set<Employee> getParentList();

    boolean checkChildInParent(int id);

    boolean createNewEmployee(Employee employee);
}

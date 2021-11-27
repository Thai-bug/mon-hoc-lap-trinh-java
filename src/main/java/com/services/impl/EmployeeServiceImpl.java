package com.services.impl;

import com.pojos.Employee;
import com.repositories.EmployeeRepository;
import com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee login(String email, String password) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if (employee == null) {
            return null;
        }
        if(employee.getStatus() == false)
            return null;

        if(employee.getPassword().equals(password)){
            return employee;
        }
        return null;
    }

    @Override
    public boolean addUser(Employee employee) {
        return false;
    }

    @Override
    public Employee getEmployeeIn(String email) {
        return employeeRepository.getEmployeeByEmail(email);
    }

    @Override
    public List<Employee> getEmployees(int page, String kw) {
        return employeeRepository.getEmployees(page, kw);
    }

    @Override
    public long getCountAllEmployee(String kw) {
        return employeeRepository.getCountAllEmployees(kw);
    }

    @Override
    public Employee getEmployeeDetail(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public boolean updateEmployeeAvatar(Employee employee) {
        return employeeRepository.updateEmployeeAvatar(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public List<Employee> getParentList() {
        return employeeRepository.getParentsList();
    }

    @Override
    public boolean checkChildInParent(int id) {
        return employeeRepository.checkChildInParent(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = getEmployeeIn(email);
        if(employee == null)
            throw new UsernameNotFoundException("Không tìm thấy người dùng");

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(employee.getRole()));

        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), auth);
    }
}

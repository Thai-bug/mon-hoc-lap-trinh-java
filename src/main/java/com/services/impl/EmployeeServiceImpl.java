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
        List<Employee> employees = employeeRepository.getEmployeesByEmail(email);
        if (employees.size() == 0) {
            return null;
        }
        Employee employeeIn = employees.get(0);

        if(employeeIn.getStatus() == false)
            return null;

        if(employeeIn.getPassword().equals(password)){
            return employeeIn;
        }
        return null;
    }

    @Override
    public boolean addUser(Employee employee) {
        return false;
    }

    @Override
    public List<Employee> getEmployeeIn(String phoneNumber) {
        return employeeRepository.getEmployeesByEmail(phoneNumber);
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Employee> employees = getEmployeeIn(email);
        if(employees.isEmpty())
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        Employee employee = employees.get(0);

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(employee.getRole()));

        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), auth);
    }
}

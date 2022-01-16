package com.controllers;

import com.pojos.Bill;
import com.pojos.Employee;
import com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class ApiEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<Employee> getEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Employee employee = employeeService.getEmployeeIn(userDetails.getUsername());
        return new ResponseEntity<Employee>(
                employee,
                HttpStatus.OK);
    }
}

package com.controllers;

import com.pojos.Employee;
import com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String login(Model model) {
        Employee employee = new Employee();
        model.addAttribute("user", employee);
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model,
                        Employee employee) {
        Employee employeeIn = employeeService.login(employee.getPhoneNumber(), employee.getPassword());
        if (employeeIn == null) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/employees")
    public String returnPage(Model model, @RequestParam(required = false)Map<String, String> params) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        List<Employee> employees = employeeService.getEmployees(page);
        model.addAttribute("employees", employees);

        return "employee";
    }
}

package com.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pojos.Employee;
import com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Cloudinary cloudinary;

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
    public String returnPage(Model model,
                             @RequestParam(required = false)Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        String kw = params.get("kw") == null ? "" : params.get("kw");
        List<Employee> employees = employeeService.getEmployees(page, kw);
        long total = employeeService.getCountAllEmployee(kw);
        model.addAttribute("employees", employees);
        model.addAttribute("total", total);

        return "employees";
    }

    @RequestMapping("/admin/employee/{id}")
    public String employeeDetail(Model model,
                                 @PathVariable(value = "id") int id) {
        Employee employee = employeeService.getEmployeeDetail(id);
        model.addAttribute("employee", employee);
        return "employeeDetail";
    }

    @PostMapping("/admin/employee/update-avatar")
    public String updateAvatar(Model model,
                                 @ModelAttribute(value = "employee") Employee employee) {
        try {
            Map r = this.cloudinary.uploader().upload(employee.getAvatar().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String avatar = (String) r.get("secure_url");
            employee.setAvatarLink(avatar);
        } catch (IOException e) {
            System.out.println("Loi o day");
        }

        boolean updateEmployee = employeeService.updateEmployeeAvatar(employee);
        if(updateEmployee) {
            model.addAttribute("employee", employee);
            return "redirect:" + employee.getId();
        }
        return "403";
    }

    @RequestMapping("/admin/employee/update/{id}")
    public String updateEmployeeInfo(Model model,
                                 @PathVariable(value = "id") int id) {
        Employee employee = employeeService.getEmployeeDetail(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @PostMapping("/admin/employee/update/{id}")
    public String updateEmployee(Model model,
                                 @ModelAttribute(value = "employee") Employee employee) {
        model.addAttribute("employee", employee);
        boolean updateEmployee = employeeService.updateEmployeeAvatar(employee);
        if(updateEmployee) {
            model.addAttribute("employee", employee);
            return "redirect:" + employee.getId();
        }
        return "updateEmployee";
    }
}

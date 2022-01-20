package com.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pojos.Employee;
import com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
                             @RequestParam(required = false) Map<String, String> params,
                             @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

        return "employees";
    }

    @RequestMapping("/admin/employee/{id}")
    public String employeeDetail(Model model,
                                 @PathVariable(value = "id") int id) {
        boolean checkChildInParent = employeeService.checkChildInParent(id);
        if (!checkChildInParent) {
            return "403";
        }
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
        }

        boolean updateEmployee = employeeService.updateEmployeeAvatar(employee);
        if (updateEmployee) {
            model.addAttribute("employee", employee);
            return "redirect:" + employee.getId();
        }
        return "403";
    }

    @RequestMapping("/admin/employee/update/{id}")
    public String updateEmployeeInfo(Model model,
                                     @PathVariable(value = "id") int id) {
        boolean checkChildInParent = employeeService.checkChildInParent(id);
        if (!checkChildInParent) {
            return "403";
        }
        Employee employee = employeeService.getEmployeeDetail(id);
        List<Employee> parents = new ArrayList<>(employeeService.getParentList());
        model.addAttribute("employee", employee);
        model.addAttribute("parents", parents);
        return "updateEmployee";
    }

    @PostMapping("/admin/employee/update/{id}")
    public String updateEmployee(Model model,
                                 @ModelAttribute(value = "employee") Employee employee) {
        model.addAttribute("employee", employee);
        boolean updateEmployee = employeeService.updateEmployee(employee);
        if (updateEmployee) {
            model.addAttribute("employee", employee);
            return "redirect:" + employee.getId();
        }
        return "updateEmployee";
    }

    @RequestMapping("/admin/employee/create")
    public String createEmployeePage(Model model) {
        List<Employee> parents = new ArrayList<>(employeeService.getParentList());
        model.addAttribute("employee", new Employee());
        model.addAttribute("parents", parents);
        return "createEmployee";
    }

    @PostMapping("/admin/employee/create")
    public String createEmployee(
            Model model,
            @Valid Employee employee,
            BindingResult result
    ) {
        List<Employee> parents = new ArrayList<>(employeeService.getParentList());
        model.addAttribute("parents", parents);
        if (result.hasErrors()) {

            return "createEmployee";
        }
        if (!employee.getPassword().equals(employee.getConfirmPassword())) {
            model.addAttribute("errMsg", "Xác thực mật khẩu không chính xác");
            return "createEmployee";
        }

        boolean createNewEmployee = employeeService.createNewEmployee(employee);
        if (createNewEmployee) {
            return "redirect:/admin/employees";
        }
        return "createEmployee";
    }
}

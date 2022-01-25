package com.controllers;

import com.pojos.Drink;
import com.pojos.Food;
import com.pojos.Service;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;

@Controller
@RequestMapping("")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "/admin/services")
    public String services(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return "services";
    }

    @RequestMapping("/admin/services/detail/{code}")
    public String service(
            Model model,
            @PathVariable(value = "code") String code
    ) {
        Service service = serviceService.getServiceByCode(code);
        model.addAttribute("service", service);

        return "serviceDetail";
    }

    @RequestMapping("/admin/services/update/{code}")
    public String serviceUpdate(
            Model model,
            @PathVariable(value = "code") String code
    ) {
        return "serviceUpdate";
    }

    @RequestMapping("/admin/services/add")
    public String serviceAdd(
            Model model
    ) {
        model.addAttribute("service", new Service());

        return "serviceAdd";
    }

    @GetMapping("/services")
    public  String indexClientLobbies(){
        return "client-service";
    }

    @GetMapping("/services/{code}")
    public  String clientLobbyDetail(){
        return "client-service-detail";
    }
}

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
    private ServiceService service;

    @RequestMapping(value = "/admin/services")
    public String services(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return "services";
    }

    @RequestMapping("/admin/services/detail/{id}")
    public String service(
            Model model,
            @PathVariable(value = "id") int id
    ) {
        Service service = this.service.getServiceById(id);
        model.addAttribute("service", service);

        return "serviceDetail";
    }

    @RequestMapping("/admin/services/update/{id}")
    public String serviceUpdate(
            Model model,
            @PathVariable(value = "id") int id
    ) {
        Service service = this.service.getServiceById(id);
        model.addAttribute("service", service);

        return "serviceUpdate";
    }

    @PostMapping("/admin/services/update/{id}")
    public String update(
            Model model,
            @ModelAttribute(value = "service") Service service
    ) {
        model.addAttribute("service", service);
        boolean update = this.service.update(service);
        if (update)
            return "redirect:/admin/services/detail/" + service.getId();
        return "serviceUpdate";
    }

    @RequestMapping("/admin/services/add")
    public String serviceAdd(
            Model model
    ) {
        model.addAttribute("service", new Service());

        return "serviceAdd";
    }

    @PostMapping("/admin/services/add")
    public String add(
            Model model,
            @ModelAttribute(value = "service") Service service
    ) {
        model.addAttribute("service", service);
        boolean update = this.service.add(service);
        if (update)
            return "redirect:/admin/services";

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

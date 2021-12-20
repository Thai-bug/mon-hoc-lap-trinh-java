package com.controllers;

import com.pojos.Drink;
import com.pojos.Food;
import com.pojos.Service;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Map;

@Controller
@RequestMapping("/admin/services")
public class ServiceController {
    @Autowired
    private ServiceService service;

    @RequestMapping(value = "")
    public String services(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        String kw = params.get("kw") == null ? "" : params.get("kw");
        Set<Service> services = service.getServices(kw, page);
        int total = service.getServicesCount(kw);
        model.addAttribute("services", services);
        model.addAttribute("total", total);

        return "services";
    }

    @RequestMapping("/detail/{id}")
    public String service(
            Model model,
            @PathVariable(value = "id") int id
    ) {
        Service service = this.service.getServiceById(id);
        model.addAttribute("service", service);

        return "serviceDetail";
    }

    @RequestMapping("/update/{id}")
    public String serviceUpdate(
            Model model,
            @PathVariable(value = "id") int id
    ) {
        Service service = this.service.getServiceById(id);
        model.addAttribute("service", service);

        return "serviceUpdate";
    }

    @PostMapping("/update/{id}")
    public String update(
            Model model,
            @ModelAttribute(value = "service") Service service
    ) {
        model.addAttribute("service", service);
        boolean update = this.service.update(service);
        if(update)
            return "redirect:/admin/services/detail/" + service.getId();
        return "serviceUpdate";
    }

    @RequestMapping("/add")
    public String serviceAdd(
            Model model
    ) {
        model.addAttribute("service", new Service());

        return "serviceAdd";
    }

    @PostMapping("/add")
    public String add(
            Model model,
            @ModelAttribute(value = "service") Service service
    ) {
        model.addAttribute("service", service);
        boolean update = this.service.add(service);
        if(update)
            return "redirect:/admin/services";

        return "serviceAdd";
    }
}

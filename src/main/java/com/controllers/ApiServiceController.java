package com.controllers;

import com.pojos.Service;
import com.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/services")
public class ApiServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/select2/service-by-name")
    public ResponseEntity<Set<Service>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        boolean status = params.get("status") == null ? true : Boolean.parseBoolean(params.get("status"));
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Set<Service> services = serviceService.getServicesByName(name, status, page);
        return new ResponseEntity<Set<Service>>(
                services,
                HttpStatus.OK);
    }
}

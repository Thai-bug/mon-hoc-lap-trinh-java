package com.controllers;

import com.pojos.Type;
import com.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/admin/types")
public class ApiTypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("")
    public ResponseEntity<Object> getDetail(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name").toString().toLowerCase(Locale.ROOT);
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Set<Type> type = typeService.getActiveType(name, page);
        return new ResponseEntity<Object>(
                type,
                HttpStatus.OK);
    }
}


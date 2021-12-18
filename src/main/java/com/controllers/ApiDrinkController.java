package com.controllers;

import com.pojos.Drink;
import com.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/drinks")
public class ApiDrinkController {
    @Autowired
    private DrinkService drinkService;

    @GetMapping("/select2/drink-by-name")
    public ResponseEntity<List<Drink>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        boolean status = params.get("status") == null ? true : Boolean.parseBoolean(params.get("status"));
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        List<Drink> drinks = drinkService.getDrinkByName(name, status, page);
        return new ResponseEntity<List<Drink>>(
                drinks,
                HttpStatus.OK);
    }
}

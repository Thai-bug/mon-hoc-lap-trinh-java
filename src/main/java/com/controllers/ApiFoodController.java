package com.controllers;

import com.pojos.Bill;
import com.pojos.Food;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/foods")
public class ApiFoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/select2/food-by-name")
    public ResponseEntity<Set<Food>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        boolean status = params.get("status") == null ? true : Boolean.parseBoolean(params.get("status"));
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Set<Food> foods = foodService.getFoodsByName(name, status, page);
        return new ResponseEntity<Set<Food>>(
                foods,
                HttpStatus.OK);
    }
}

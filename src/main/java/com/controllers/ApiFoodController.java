package com.controllers;

import com.pojos.Bill;
import com.pojos.Food;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/foods")
public class ApiFoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/select2/food-by-name")
    public ResponseEntity<List<Food>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        boolean status = params.get("status") == null ? true : Boolean.parseBoolean(params.get("status"));
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        List<Food> foods = foodService.getFoodsByName(name, status, page);
        return new ResponseEntity<List<Food>>(
                foods,
                HttpStatus.OK);
    }
}

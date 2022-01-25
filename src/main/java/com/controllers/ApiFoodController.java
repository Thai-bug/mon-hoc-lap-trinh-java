package com.controllers;

import com.pojos.Bill;
import com.pojos.Drink;
import com.pojos.Food;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiFoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/admin/foods/select2/food-by-name")
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

    @PostMapping(value = "/admin/foods/get_all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestBody Map<String, Object> json
    ) {
        int start = json.get("start") == null ? 1 : Integer.parseInt(json.get("start").toString()) + 1;
        int length = json.get("length") == null ? 0 : Integer.parseInt(json.get("length").toString());
        Map<String, String> searchObj= (Map<String, String>) json.get("search");
        long total = foodService.getFoodCount(searchObj.get("value"));
        Set<Food> data = foodService.getFoods(searchObj.get("value"), start, length);
        Map<String, Object> result = new HashMap<>() ;
        result.put("data", data);
        result.put("recordsFiltered", total);
        result.put("recordsTotal", total);
        result.put("draw", json.get("draw"));
        return new ResponseEntity(
                result,
                HttpStatus.OK);
    }

    @GetMapping(value = "/foods")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getFoodsForClient(
            @RequestParam(required = false) Map<String, String> params
    ){
        try{
            int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
            int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit"));
            String kw = params.get("kw") == null ? "" : params.get("kw").toLowerCase(Locale.ROOT);
            Map<String, Object> result = foodService.getFoodsForClient(page, limit, kw);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/foods/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Food>> getDrinkForClient(
            @PathVariable(value = "code") String code
    ){
        try{
            return new ResponseEntity<>(foodService.getClientFoodByCode(code), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }
}

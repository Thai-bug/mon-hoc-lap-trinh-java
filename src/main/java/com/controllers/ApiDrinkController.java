package com.controllers;

import com.pojos.Drink;
import com.pojos.Employee;
import com.services.DrinkService;
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
public class ApiDrinkController {
    @Autowired
    private DrinkService drinkService;

    @GetMapping("/admin/drinks/select2/drink-by-name")
    public ResponseEntity<Set<Drink>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        boolean status = params.get("status") == null ? true : Boolean.parseBoolean(params.get("status"));
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Set<Drink> drinks = drinkService.getDrinkByName(name, status, page);
        return new ResponseEntity<Set<Drink>>(
                drinks,
                HttpStatus.OK);
    }

    @PostMapping(value = "/admin/drinks/get_all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestBody Map<String, Object> json
    ) {
        int start = json.get("start") == null ? 1 : Integer.parseInt(json.get("start").toString()) + 1;
        int length = json.get("length") == null ? 0 : Integer.parseInt(json.get("length").toString());
        Map<String, String> searchObj= (Map<String, String>) json.get("search");
        long total = drinkService.getCountDrinks(searchObj.get("value"));
        Set<Drink> data = drinkService.getDrinks(searchObj.get("value"), start, length);
        Map<String, Object> result = new HashMap<>() ;
        result.put("data", data);
        result.put("recordsFiltered", total);
        result.put("recordsTotal", total);
        result.put("draw", json.get("draw"));
        return new ResponseEntity(
                result,
                HttpStatus.OK);
    }

    @GetMapping(value = "/drinks")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getDrinksForClient(
            @RequestParam(required = false) Map<String, String> params
    ){
        try{
            int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
            int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit"));
            String kw = params.get("kw") == null ? "" : params.get("kw").toLowerCase(Locale.ROOT);
            Map<String, Object> result = drinkService.getDrinksForClient(page, limit, kw);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping(value = "/drinks/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getDrinkForClient(
            @PathVariable(value = "code") String code
    ){
        try{

            return new ResponseEntity<>(drinkService.getClientDrinkByCode(code), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }
}

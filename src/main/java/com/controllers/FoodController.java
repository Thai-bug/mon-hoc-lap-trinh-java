package com.controllers;

import com.pojos.Drink;
import com.pojos.Food;
import com.services.FoodService;
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
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping("/admin/foods")
    public String getFoods(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return "food";
    }

    @RequestMapping("/admin/food/detail/{code}")
    public String foodDetail(
            Model model,
            @PathVariable(value = "code") String code) {
        Food food = foodService.getFoodByCode(code);
        model.addAttribute("food", food);

        return "foodDetail";
    }

    @RequestMapping("/admin/foods/update/{code}")
    public String getUpdateDrink(Model model, @PathVariable(value = "code") String code) {
        return "foodUpdate";
    }

    @RequestMapping("/admin/food/add")
    public String addFood(
            Model model
    ) {
        model.addAttribute("food", new Food());
        return "foodAdd";
    }

    @GetMapping(value = "/foods")
    public String clientFoods(){
        return "client-food";
    }

    @GetMapping(value = "/foods/{code}")
    public String clientFoodDetail(){
        return "client-food-detail";
    }
}

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
@RequestMapping("/admin/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping("")
    public String getFoods(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        String kw = params.get("kw") == null ? "" : params.get("kw");
        List<Food> foods = new ArrayList<>(foodService.getFoods(kw, page));
        int total = foodService.getFoodCount(kw);
        model.addAttribute("foods", foods);
        model.addAttribute("total", total);

        return "food";
    }

    @RequestMapping("/detail/{id}")
    public String foodDetail(
            Model model,
            @PathVariable(value = "id") int id) {
        Food food = foodService.getFoodById(id);
        model.addAttribute("food", food);

        return "foodDetail";
    }

    @RequestMapping("/update/{id}")
    public String getUpdateDrink(Model model, @PathVariable(value = "id") int id) {
        Food food = foodService.getFoodById(id);
        model.addAttribute("food", food);

        return "foodUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateDrink(
            Model model,
            @ModelAttribute(value = "food") Food food) {
        model.addAttribute("food", food);
        boolean updateFood = foodService.update(food);
        if (updateFood)
            return "redirect:/admin/food/detail/" + food.getId();

        return "foodUpdate";
    }

    @RequestMapping("/add")
    public String addFood(
            Model model
    ) {
        model.addAttribute("food", new Food());
        return "foodAdd";
    }

    @PostMapping("/add")
    public String add(
            Model model,
            @ModelAttribute(value = "food") Food food
    ) {
        model.addAttribute("food", food);
        boolean add = foodService.add(food);
        if (add)
            return "redirect:/admin/food";
        return "foodAdd";
    }
}

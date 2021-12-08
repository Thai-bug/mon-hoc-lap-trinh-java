package com.controllers;

import com.pojos.Drink;
import com.pojos.Food;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping("")
    public String getFoods(
            Model  model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        String kw = params.get("kw") == null ? "" : params.get("kw");
        List<Food> foods = foodService.getFoods(kw, page);
        int total = foodService.getFoodCount(kw);
        model.addAttribute("foods", foods);
        model.addAttribute("total", total);

        return "foods";
    }

    @RequestMapping("/detail/{id}")
    public String foodDetail(Model model, @PathVariable(value = "id") int id) {
        Food food = foodService.getFoodById(id);

        model.addAttribute("food", food);
        return "foodDetail";
    }
}

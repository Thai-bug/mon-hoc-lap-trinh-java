package com.controllers;

import com.pojos.Drink;
import com.pojos.Lobby;
import com.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @RequestMapping("/admin/drinks")
    public String showDrinks(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return "drinks";
    }

    @RequestMapping("/admin/drinks/detail/{code}")
    public String drinkDetail(Model model,
                              @PathVariable(value = "code") String code) {
        Drink drink = drinkService.getDrinkByCode(code);

        model.addAttribute("drink", drink);
        return "drinkDetail";
    }

    @RequestMapping("/admin/drinks/update/{code}")
    public String getUpdateDrink(Model model, @PathVariable(value = "code") String code) {
//        Drink drink = drinkService.getDrinkById(id);
//
//        model.addAttribute("drink", drink);
        return "drinkUpdate";
    }

    @RequestMapping("/admin/drinks/add")
    public String addDrink(
            Model model) {
        model.addAttribute("drink", new Drink());
        return "drinkAdd";
    }


    @PostMapping("/admin/drinks/add")
    public String add(
            Model model,
            @ModelAttribute(value = "drink") Drink drink) {
        model.addAttribute("drink", drink);
        boolean add = drinkService.add(drink);
        if (add)
            return "redirect:/admin/drinks";
        return "drinkAdd";
    }

    @GetMapping("/drinks")
    public String clientDrinks(){
        return "client-drink";
    }

    @GetMapping("/drinks/{code}")
    public String clientDrinkDetail(){
        return "client-drink-detail";
    }
}

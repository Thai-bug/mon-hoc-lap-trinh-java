package com.controllers;

import com.pojos.Drink;
import com.pojos.Lobby;
import com.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Map;

@Controller
@RequestMapping("/admin/drinks")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @RequestMapping("")
    public String showDrinks(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        String kw = params.get("kw") == null ? "" : params.get("kw");

        Set<Drink> drinks = drinkService.getDrinks(kw, page);
        int total = drinkService.getCountDrinks(kw);

        model.addAttribute("drinks", drinks);
        model.addAttribute("total", total);
        return "drinks";
    }

    @RequestMapping("/detail/{id}")
    public String drinkDetail(Model model,
                              @PathVariable(value = "id") int id) {
        Drink drink = drinkService.getDrinkById(id);

        model.addAttribute("drink", drink);
        return "drinkDetail";
    }

    @RequestMapping("/update/{id}")
    public String getUpdateDrink(Model model, @PathVariable(value = "id") int id) {
        Drink drink = drinkService.getDrinkById(id);

        model.addAttribute("drink", drink);
        return "drinkUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateDrink(
            Model model,
            @ModelAttribute(value = "drink") Drink drink) {
        model.addAttribute("drink", drink);
        boolean updateDrink = drinkService.updateDrink(drink);
        if (updateDrink)
            return "redirect:/admin/drinks/detail/" + drink.getId();

        return "drinkUpdate";
    }

    @RequestMapping("/add")
    public String addDrink(
            Model model) {
        model.addAttribute("drink", new Drink());
        return "drinkAdd";
    }


    @PostMapping("/add")
    public String add(
            Model model,
            @ModelAttribute(value = "drink") Drink drink) {
        model.addAttribute("drink", drink);
        boolean add = drinkService.add(drink);
        if (add)
            return "redirect:/admin/drinks";
        return "drinkAdd";
    }

}

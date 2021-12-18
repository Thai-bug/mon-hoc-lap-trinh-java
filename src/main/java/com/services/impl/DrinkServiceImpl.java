package com.services.impl;

import com.pojos.Drink;
import com.repositories.DrinkRepository;
import com.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public List<Drink> getDrinks(String kw, int page) {
        return drinkRepository.getDrinks(kw, page);
    }

    @Override
    public int getCountDrinks(String kw) {
        return drinkRepository.getCountDrinks(kw);
    }

    @Override
    public Drink getDrinkById(int id) {
        return drinkRepository.getDrinkById(id);
    }

    @Override
    public boolean updateDrink(Drink drink) {
        return drinkRepository.updateDrink(drink);
    }

    @Override
    public boolean add(Drink drink) {
        return drinkRepository.add(drink);
    }

    @Override
    public List<Drink> getDrinkByName(String name, boolean status, int page) {
        return drinkRepository.getDrinksByName(name, status, page);
    }
}

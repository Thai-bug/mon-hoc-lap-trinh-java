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
}

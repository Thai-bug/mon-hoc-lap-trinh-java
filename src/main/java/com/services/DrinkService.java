package com.services;

import com.pojos.Drink;

import java.util.Set;

public interface DrinkService {
    Set<Drink> getDrinks(String kw, int page, int length);

    int getCountDrinks(String kw);

    Drink getDrinkById(int id);

    boolean updateDrink(Drink drink);

    boolean add(Drink drink);

    Set<Drink> getDrinkByName(String name, boolean status, int page);
}

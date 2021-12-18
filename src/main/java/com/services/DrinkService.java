package com.services;

import com.pojos.Drink;

import java.util.List;

public interface DrinkService {
    List<Drink> getDrinks(String kw, int page);

    int getCountDrinks(String kw);

    Drink getDrinkById(int id);

    boolean updateDrink(Drink drink);

    boolean add(Drink drink);

    List<Drink> getDrinkByName(String name, boolean status, int page);
}

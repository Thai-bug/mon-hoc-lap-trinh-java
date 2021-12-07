package com.repositories;

import com.pojos.Drink;

import java.util.List;

public interface DrinkRepository {
    public List<Drink> getDrinks(String kw, int page);

    public int getCountDrinks(String kw);

    public Drink getDrinkById(int id);

    public boolean updateDrink(Drink drink);
}

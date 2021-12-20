package com.repositories;

import com.pojos.Drink;

import java.util.Set;

public interface DrinkRepository {
    public Set<Drink> getDrinks(String kw, int page);

    public int getCountDrinks(String kw);

    public Drink getDrinkById(int id);

    public boolean updateDrink(Drink drink);

    public boolean add(Drink drink);

    public Set<Drink> getDrinksByName(String name, boolean status, int page);
}

package com.repositories;

import com.pojos.Drink;

import java.util.Set;

public interface DrinkRepository {
    public Set<Drink> getDrinks(String kw, int page, int length);

    public int getCountDrinks(String kw);

    public Drink getDrinkById(int id);

    public boolean updateDrink(Drink drink);

    public boolean add(Drink drink);

    public Set<Drink> getDrinksByName(String name, boolean status, int page);

    Set<Drink> getDrinksForClient(int page, int limit, String kw);

    int countDrinkClient(String kw);

    Drink getClientDrinkByCode(String code);
}

package com.services;

import com.pojos.Drink;
import com.pojos.Lobby;

import java.util.Map;
import java.util.Set;

public interface DrinkService {
    Set<Drink> getDrinks(String kw, int page, int length);

    int getCountDrinks(String kw);

    Drink getDrinkByCode(String code);

    boolean updateDrink(Map<String, Object> json);

    boolean add(Drink drink);

    Set<Drink> getDrinkByName(String name, boolean status, int page);

    Map<String, Object> getDrinksForClient(int page, int limit, String kw);

    Map<String, Object> getClientDrinkByCode(String code);
}

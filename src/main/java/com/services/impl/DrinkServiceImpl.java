package com.services.impl;

import com.pojos.Drink;
import com.pojos.Lobby;
import com.repositories.DrinkRepository;
import com.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public Set<Drink> getDrinks(String kw, int page, int length) {
        return drinkRepository.getDrinks(kw, page, length);
    }

    @Override
    public int getCountDrinks(String kw) {
        return drinkRepository.getCountDrinks(kw);
    }

    @Override
    public Drink getDrinkByCode(String code) {
        return drinkRepository.getDrinkByCode(code);
    }

    @Override
    public boolean updateDrink(Map<String, Object> json) {
        Drink drink = drinkRepository.getDrinkByCode(json.get("code").toString());
        drink.setName( json.get("name").toString());
        drink.setDescription( json.get("description").toString());
        drink.setPrice(Integer.parseInt(json.get("price").toString()));
        drink.setUnit(json.get("unit").toString());
        drink.setStatus((boolean) json.get("status"));

        return drinkRepository.updateDrink(drink);
    }

    @Override
    public boolean createDrink(Map<String, Object> json) {
        Drink drink = new Drink();
        drink.setCode(UUID.randomUUID().toString());
        drink.setName( json.get("name").toString());
        drink.setPrice(Integer.parseInt(json.get("price").toString()));
        drink.setUnit(json.get("unit").toString());
        drink.setStatus((boolean) json.get("status"));
        drink.setCreatedAt(new Date());
        drink.setDescription( json.get("description").toString());

        return drinkRepository.add(drink);
    }

    @Override
    public Set<Drink> getDrinkByName(String name, boolean status, int page) {
        return drinkRepository.getDrinksByName(name, status, page);
    }

    @Override
    public Map<String, Object> getDrinksForClient(int page, int limit, String kw) {
        Set<Drink> drinks = drinkRepository.getDrinksForClient( page, limit, kw);

        int total = drinkRepository.countDrinkClient(kw);

        Map<String, Object> result = new HashMap<>() ;

        result.put("total", total);
        result.put("data", drinks);
        return result;
    }

    @Override
    public Map<String, Object> getClientDrinkByCode(String code) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", drinkRepository.getClientDrinkByCode(code));
        return data;
    }
}

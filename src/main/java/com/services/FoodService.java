package com.services;

import com.pojos.Food;
import com.pojos.Lobby;

import java.util.Map;
import java.util.Set;

public interface FoodService {
    Set<Food> getFoods(String kw, int page, int length);

    int getFoodCount(String kw);

    Food getFoodByCode(String code);

    boolean updateFood(Map<String, Object> json);

    boolean createFood(Map<String, Object> json);

    Set<Food> getFoodsByName(String name, boolean status, int page);

    Map<String, Object> getFoodsForClient(int page, int limit, String kw);

    Map<String, Food> getClientFoodByCode(String code);
}

package com.services;

import com.pojos.Food;

import java.util.Set;

public interface FoodService {
    Set<Food> getFoods(String kw, int page, int length);

    int getFoodCount(String kw);

    Food getFoodById(int id);

    boolean update(Food food);

    boolean add(Food food);

    Set<Food> getFoodsByName(String name, boolean status, int page);
}

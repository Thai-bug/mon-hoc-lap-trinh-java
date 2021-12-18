package com.services;

import com.pojos.Food;

import java.util.List;

public interface FoodService {
    List<Food> getFoods(String kw, int page);

    int getFoodCount(String kw);

    Food getFoodById(int id);

    boolean update(Food food);

    boolean add(Food food);

    List<Food> getFoodsByName(String name, boolean status, int page);
}

package com.repositories;

import com.pojos.Food;

import java.util.List;

public interface FoodRepository {
    List<Food> getFoods(String kw, int page);

    int getFoodCount(String kw);

    Food getFoodById(int id);

    boolean update(Food food);

    boolean add(Food food);
}

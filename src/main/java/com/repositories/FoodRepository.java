package com.repositories;

import com.pojos.Food;

import java.util.Set;

public interface FoodRepository {
    Set<Food> getFoods(String kw, int page, int length);

    int getFoodCount(String kw);

    Food getFoodByCode(String code);

    boolean update(Food food);

    boolean add(Food food);

    Set<Food> getFoodsByName(String name, boolean status, int page);

    Set<Food> getFoodsForClient(int page, int limit, String kw);

    int countFoodClient(String kw);

    Food getClientFoodByCode(String code);
}

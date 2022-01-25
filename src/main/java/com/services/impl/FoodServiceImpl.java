package com.services.impl;

import com.pojos.Drink;
import com.pojos.Food;
import com.repositories.FoodRepository;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Set<Food> getFoods(String kw, int page, int length) {
        return foodRepository.getFoods(kw, page, length);
    }

    @Override
    public int getFoodCount(String kw) {
        return foodRepository.getFoodCount(kw);
    }

    @Override
    public Food getFoodByCode(String code) {
        return foodRepository.getFoodByCode(code);
    }

    @Override
    public boolean updateFood(Map<String, Object> json) {
        Food food = foodRepository.getFoodByCode(json.get("code").toString());
        food.setName( json.get("name").toString());
        food.setDescription( json.get("description").toString());
        food.setPrice(Integer.parseInt(json.get("price").toString()));
        food.setUnit(json.get("unit").toString());
        food.setStatus((boolean) json.get("status"));

        return foodRepository.update(food);
    }

    @Override
    public boolean createFood(Map<String, Object> json) {
        Food food = new Food();
        food.setCode(UUID.randomUUID().toString());
        food.setName( json.get("name").toString());
        food.setPrice(Integer.parseInt(json.get("price").toString()));
        food.setUnit(json.get("unit").toString());
        food.setStatus((boolean) json.get("status"));
        food.setCreatedAt(new Date());
        food.setDescription( json.get("description").toString());

        return foodRepository.add(food);
    }

    @Override
    public Set<Food> getFoodsByName(String name, boolean status, int page) {
        return foodRepository.getFoodsByName(name, status, page);
    }

    @Override
    public Map<String, Object> getFoodsForClient(int page, int limit, String kw) {
        Set<Food> lobbies = foodRepository.getFoodsForClient( page, limit, kw);

        int total = foodRepository.countFoodClient(kw);

        Map<String, Object> result = new HashMap<>() ;

        result.put("total", total);
        result.put("data", lobbies);

        return result;
    }

    @Override
    public Map<String, Food> getClientFoodByCode(String code) {
        Map<String, Food> data = new HashMap<>();
        data.put("result", foodRepository.getClientFoodByCode(code));
        return data;
    }
}

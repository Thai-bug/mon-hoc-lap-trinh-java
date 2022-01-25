package com.services.impl;

import com.pojos.Food;
import com.repositories.FoodRepository;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public Food getFoodById(int id) {
        return foodRepository.getFoodById(id);
    }

    @Override
    public boolean update(Food food) {
        return foodRepository.update(food);
    }

    @Override
    public boolean add(Food food) {
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

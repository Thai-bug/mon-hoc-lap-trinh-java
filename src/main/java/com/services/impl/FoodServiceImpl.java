package com.services.impl;

import com.pojos.Food;
import com.repositories.FoodRepository;
import com.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getFoods(String kw, int page) {
        return foodRepository.getFoods(kw, page);
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
}

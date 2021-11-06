package com.controllers;

import com.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
@Autowired
    private CategoryService categoryService;

    @RequestMapping("/")
    public String getCategories(Model model){
        return "index";
    }

    @RequestMapping("/create")
    @Transactional
    public String createCategory(Model model){
        return "category/create";
    }
}

package com.controllers;

import com.pojos.Category;
import com.pojos.Product;
import com.services.CategoryService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @ModelAttribute
    public void commonAttr(Model model){
        List<Category> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam(value = "kw", required = false, defaultValue = "") String kw){
        List<Product> products = this.productService.getProductsByName(kw);
        model.addAttribute("products", products);
        return "index";
    }
}
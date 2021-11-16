package com.controllers;

import com.pojos.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {
    @GetMapping("/admin/products")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

//    @GetMapping("/admin/products/create")
//    public String createProduct(Model model) {
//        model.addAttribute("product", new Product());
//        return "product";
//    }

    @PostMapping("/admin/products/create")
    public String createProduct(Product product) {
        return "product";
    }
}

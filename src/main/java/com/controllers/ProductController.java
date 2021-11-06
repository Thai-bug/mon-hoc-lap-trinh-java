package com.controllers;

import com.pojos.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductController {
    @GetMapping("/admin/products")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }
}

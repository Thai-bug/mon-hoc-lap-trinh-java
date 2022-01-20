package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
public class HomeController {
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private ProductService productService;

    @ModelAttribute
    public void commonAttr(Model model){

//        Set<Category> categories = this.categoryService.getAllCategories();
//        model.addAttribute("categories", categories);s
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam(value = "kw", required = false, defaultValue = "") String kw){
//        Set<Product> products = this.productService.getProductsByName(kw);
//        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/general")
    public String general(Model model, @RequestParam(value = "kw", required = false, defaultValue = "") String kw){
//        Set<Product> products = this.productService.getProductsByName(kw);
//        model.addAttribute("products", products);
        return "generalPage";
    }
}
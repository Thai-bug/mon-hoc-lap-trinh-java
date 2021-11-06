package com.services;

import com.pojos.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByName(String kw);
}

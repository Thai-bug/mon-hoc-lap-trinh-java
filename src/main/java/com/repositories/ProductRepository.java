package com.repositories;

import com.pojos.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsByName(String kw);
}

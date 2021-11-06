package com.services.impl;

import com.pojos.Product;
import com.repositories.ProductRepository;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getProductsByName(String kw) {
        return productRepository.getProductsByName(kw);
    }
}

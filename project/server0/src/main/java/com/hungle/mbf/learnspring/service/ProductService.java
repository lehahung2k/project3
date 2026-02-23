package com.hungle.mbf.learnspring.service;

import com.hungle.mbf.learnspring.entities.Products;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public List<Products> getAllProducts();
    public void saveProduct(Products products);
    public Products findProductById(Integer pId);
    public Products deleteProduct(Integer pId);
}

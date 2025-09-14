package com.example.service;

import com.example.entity.Product;
import com.example.exception.JobPortalException;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByMarketId(String marketId) throws JobPortalException;

    Product getProductById(String productId) throws JobPortalException;
}

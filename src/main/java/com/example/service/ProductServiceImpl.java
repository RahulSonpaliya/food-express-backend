package com.example.service;

import com.example.entity.Product;
import com.example.exception.JobPortalException;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductsByMarketId(String marketId) throws JobPortalException {
        try {
            return productRepository.findByMarketId(Long.parseLong(marketId));
        } catch (Exception e) {
            throw new JobPortalException("Error while fetching products for marketId: " + marketId);
        }
    }
}

package com.example.service;

import com.example.entity.Product;
import com.example.exception.JobPortalException;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public Product getProductById(String productId) throws JobPortalException {
        return productRepository.findById(Long.parseLong(productId)).orElseThrow(() -> new JobPortalException("PRODUCT_NOT_FOUND"));
    }
}

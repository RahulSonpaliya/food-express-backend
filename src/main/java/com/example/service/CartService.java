package com.example.service;

import com.example.exception.JobPortalException;
import com.example.model.request.AddToCartRequest;

public interface CartService {
    Long addToCart(Long userId, AddToCartRequest request) throws JobPortalException;
}

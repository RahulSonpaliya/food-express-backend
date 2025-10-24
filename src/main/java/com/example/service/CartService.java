package com.example.service;

import com.example.exception.JobPortalException;
import com.example.model.request.AddToCartRequest;
import com.example.model.request.UpdateCartRequest;
import com.example.model.response.GetCartResponse;

public interface CartService {
    Long addToCart(Long userId, AddToCartRequest request) throws JobPortalException;

    void updateCart(long cartId, UpdateCartRequest updateCartRequest) throws JobPortalException;

    void deleteCart(long cartId) throws JobPortalException;

    GetCartResponse getCart(Long userId) throws JobPortalException;
}

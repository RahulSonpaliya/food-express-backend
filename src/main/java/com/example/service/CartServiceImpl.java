package com.example.service;

import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.exception.JobPortalException;
import com.example.model.request.AddToCartRequest;
import com.example.repository.CartRepository;
import com.example.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Long addToCart(Long userId, AddToCartRequest request) throws JobPortalException {
        var cart = new Cart();
        cart.setId(Utilities.getNextSequence("carts"));
        cart.setUserId(userId);
        var cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(request.getQuantity(), request.getProductId(), request.getVariantId()));
        cart.setItems(cartItems);
        var savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }
}

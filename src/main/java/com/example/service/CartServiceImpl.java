package com.example.service;

import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.exception.JobPortalException;
import com.example.model.request.AddToCartRequest;
import com.example.model.request.UpdateCartRequest;
import com.example.model.response.GetCartResponse;
import com.example.repository.CartRepository;
import com.example.repository.MarketRepository;
import com.example.repository.ProductRepository;
import com.example.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public Long addToCart(Long userId, AddToCartRequest request) throws JobPortalException {
        var cart = new Cart();
        CartItem item = new CartItem(request.getQuantity(), request.getProductId(), request.getVariantId());
        var cartCheck = cartRepository.findByUserId(userId);
        if(cartCheck.isPresent()) {
            cart = cartCheck.get();
            cart.getItems().add(item);
        } else {
            cart.setId(Utilities.getNextSequence("carts"));
            cart.setUserId(userId);
            var cartItems = new ArrayList<CartItem>();
            cartItems.add(item);
            cart.setItems(cartItems);
        }
        var savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }

    @Override
    public void updateCart(long cartId, UpdateCartRequest updateCartRequest) throws JobPortalException {
        var cart = cartRepository.findById(cartId).orElseThrow(() -> new JobPortalException("CART_NOT_FOUND"));
        if(updateCartRequest.getQuantity() > 0) {
            for (CartItem item : cart.getItems()) {
                if (item.getProductId().equals(updateCartRequest.getProductId())){
                    item.setQuantity(updateCartRequest.getQuantity());
                }
            }
        } else {
            cart.getItems().removeIf(e -> Objects.equals(e.getProductId(), updateCartRequest.getProductId()));
        }
        cartRepository.save(cart);
        if(cart.getItems().isEmpty()) {
            deleteCart(cartId);
        }
    }

    @Override
    public void deleteCart(long cartId) throws JobPortalException {
        var cart = cartRepository.findById(cartId).orElseThrow(() -> new JobPortalException("CART_NOT_FOUND"));
        cartRepository.delete(cart);
    }

    @Override
    public GetCartResponse getCart(Long userId) throws JobPortalException {
        var cart = cartRepository.findByUserId(userId).orElseThrow(() -> new JobPortalException("CART_NOT_FOUND"));
        var response = new GetCartResponse("Get cart success", true);
        response.setCartId(cart.getId());
        var products = cart.getItems().stream().map(item -> {
            var product = productRepository.findById(item.getProductId()).get();
            product.setQty(item.getQuantity().toString());
            if(response.getVendorDetail() == null) {
                response.setVendorDetail(marketRepository.findById(product.getMarketId()).get());
            }
            return product;
        }).toList();
        response.setProducts(products);
        response.setDeliveryPrice(0);
        return response;
    }
}

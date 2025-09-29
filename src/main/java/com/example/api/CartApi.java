package com.example.api;

import com.example.exception.JobPortalException;
import com.example.model.request.AddToCartRequest;
import com.example.model.response.AddToCartResponse;
import com.example.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartApi {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestHeader("User-Id") String userId, @RequestBody @Valid AddToCartRequest addToCartRequest) throws JobPortalException {
        var cartId = cartService.addToCart(Long.parseLong(userId), addToCartRequest);
        AddToCartResponse addToCartSuccess = new AddToCartResponse("Add to cart success", true);
        addToCartSuccess.setCartId(cartId);
        return new ResponseEntity<>(addToCartSuccess, HttpStatus.OK);
    }

}

package com.example.api;

import com.example.exception.JobPortalException;
import com.example.model.request.AddToCartRequest;
import com.example.model.request.UpdateCartRequest;
import com.example.model.response.AddToCartResponse;
import com.example.model.response.BaseResponse;
import com.example.model.response.GetCartResponse;
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

    @PostMapping("/update/{cartId}")
    public ResponseEntity<BaseResponse> updateCart(@PathVariable(value = "cartId") String cartId, @RequestBody @Valid UpdateCartRequest updateCartRequest) throws JobPortalException {
        cartService.updateCart(Long.parseLong(cartId), updateCartRequest);
        return new ResponseEntity<>(new BaseResponse("Cart Updated", true), HttpStatus.OK);
    }

    @PostMapping("/delete/{cartId}")
    public ResponseEntity<BaseResponse> deleteCart(@PathVariable(value = "cartId") String cartId) throws JobPortalException {
        cartService.deleteCart(Long.parseLong(cartId));
        return new ResponseEntity<>(new BaseResponse("Cart Deleted", true), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<GetCartResponse> getCart(@RequestHeader("User-Id") String userId) {
        return new ResponseEntity<>(new GetCartResponse("Cart get success", true), HttpStatus.OK);
    }

}

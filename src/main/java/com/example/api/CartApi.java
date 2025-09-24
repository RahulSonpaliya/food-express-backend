package com.example.api;

import com.example.model.request.AddToCartRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartApi {

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestHeader("User-Id") String userId, @RequestBody @Valid AddToCartRequest addToCartRequest) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}

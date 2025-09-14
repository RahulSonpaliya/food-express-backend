package com.example.api;

import com.example.exception.JobPortalException;
import com.example.model.response.ProductDetailResponse;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/detail/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable(value = "productId") String productId) throws JobPortalException {
        var product = productService.getProductById(productId);
        var productDetailResponse = new ProductDetailResponse("Success", true);
        productDetailResponse.setProduct(product);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.OK);
    }
}

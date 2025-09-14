package com.example.model.response;

import com.example.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDetailResponse extends BaseResponse {
    private Product product;

    public ProductDetailResponse(String message, boolean success) {
        super(message, success);
    }
}

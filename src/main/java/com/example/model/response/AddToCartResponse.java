package com.example.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddToCartResponse extends BaseResponse{

    private Long cartId;

    public AddToCartResponse(String message, boolean success) {
        super(message, success);
    }
}

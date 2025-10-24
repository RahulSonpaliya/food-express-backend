package com.example.model.response;

import com.example.entity.Market;
import com.example.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetCartResponse extends BaseResponse{

    private Long cartId;
    private Market vendorDetail;
    private double deliveryPrice;
    private List<Product> products;

    public GetCartResponse(String message, boolean success) {
        super(message, success);
    }
}
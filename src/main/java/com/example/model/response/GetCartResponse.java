package com.example.model.response;

import com.example.entity.Market;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetCartResponse extends BaseResponse{

    private Long cartId;
    private Market vendorDetail;
    private double deliveryPrice;

    public GetCartResponse(String message, boolean success) {
        super(message, success);
    }
}

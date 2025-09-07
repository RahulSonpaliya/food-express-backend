package com.example.model.response;

import com.example.entity.Market;
import com.example.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MarketDetailResponse extends BaseResponse {
    private Market market;
    private List<Product> products;

    public MarketDetailResponse(String message, boolean success) {
        super(message, success);
    }
}

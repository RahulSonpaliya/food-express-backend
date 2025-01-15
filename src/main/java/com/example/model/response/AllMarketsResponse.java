package com.example.model.response;

import com.example.entity.Market;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AllMarketsResponse extends BaseResponse {

    private List<Market> marketList;

    public AllMarketsResponse(String message, boolean success) {
        super(message, success);
    }
}

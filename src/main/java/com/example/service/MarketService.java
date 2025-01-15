package com.example.service;

import com.example.model.request.AllMarketsRequest;
import com.example.model.response.AllMarketsResponse;

public interface MarketService {
    AllMarketsResponse getNearbyMarkets(AllMarketsRequest allMarketsRequest);
}

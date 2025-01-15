package com.example.service;

import com.example.model.request.NearbyMarketsRequest;
import com.example.model.response.NearbyMarketsResponse;

public interface MarketService {
    NearbyMarketsResponse getNearbyMarkets(NearbyMarketsRequest allMarketsRequest);
}

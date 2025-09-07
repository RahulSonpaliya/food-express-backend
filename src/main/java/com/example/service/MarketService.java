package com.example.service;

import com.example.entity.Market;
import com.example.exception.JobPortalException;
import com.example.model.request.NearbyMarketsRequest;
import com.example.model.response.NearbyMarketsResponse;

public interface MarketService {
    NearbyMarketsResponse getNearbyMarkets(NearbyMarketsRequest allMarketsRequest);
    Market getMarketById(String marketId) throws JobPortalException;
}

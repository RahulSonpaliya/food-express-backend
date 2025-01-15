package com.example.service;

import com.example.entity.Market;
import com.example.model.request.NearbyMarketsRequest;
import com.example.model.response.NearbyMarketsResponse;
import com.example.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public NearbyMarketsResponse getNearbyMarkets(NearbyMarketsRequest nearbyMarketsRequest) {
        List<Market> markets;
        double lat = Double.parseDouble(nearbyMarketsRequest.getLatitude());
        double lng = Double.parseDouble(nearbyMarketsRequest.getLongitude());
        if (nearbyMarketsRequest.getCategoryId() != null) {
            markets = marketRepository.findMarketsWithinRadiusAndCategory(lat, lng, nearbyMarketsRequest.getCategoryId());
        } else {
            markets = marketRepository.findMarketsWithinRadius(lat, lng);
        }
        var response = new NearbyMarketsResponse("Success", true);
        response.setMarketList(markets);
        return response;
    }
}

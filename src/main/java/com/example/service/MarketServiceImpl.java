package com.example.service;

import com.example.model.request.NearbyMarketsRequest;
import com.example.model.response.NearbyMarketsResponse;
import com.example.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public NearbyMarketsResponse getNearbyMarkets(NearbyMarketsRequest nearbyMarketsRequest) {
        var markets = marketRepository.findMarketsWithinRadius(Double.parseDouble(nearbyMarketsRequest.getLatitude()), Double.parseDouble(nearbyMarketsRequest.getLongitude()));
        var response = new NearbyMarketsResponse("Success", true);
        response.setMarketList(markets);
        return response;
    }
}

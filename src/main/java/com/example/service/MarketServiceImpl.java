package com.example.service;

import com.example.model.request.AllMarketsRequest;
import com.example.model.response.AllMarketsResponse;
import com.example.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public AllMarketsResponse getNearbyMarkets(AllMarketsRequest allMarketsRequest) {
        var markets = marketRepository.findMarketsWithinRadius(Double.parseDouble(allMarketsRequest.getLatitude()), Double.parseDouble(allMarketsRequest.getLongitude()));
        var response = new AllMarketsResponse("Success", true);
        response.setMarketList(markets);
        return response;
    }
}

package com.example.api;

import com.example.model.request.NearbyMarketsRequest;
import com.example.model.response.NearbyMarketsResponse;
import com.example.service.MarketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/markets")
public class MarketApi {

    @Autowired
    private MarketService marketService;

    @PostMapping("/nearby")
    public ResponseEntity<NearbyMarketsResponse> getNearbyMarkets(@RequestBody @Valid NearbyMarketsRequest allMarketsRequest) {
        return new ResponseEntity<>(marketService.getNearbyMarkets(allMarketsRequest), HttpStatus.OK);
    }
}

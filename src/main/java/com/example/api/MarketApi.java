package com.example.api;

import com.example.model.request.AllMarketsRequest;
import com.example.model.response.AllMarketsResponse;
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
    public ResponseEntity<AllMarketsResponse> getNearbyMarkets(@RequestBody @Valid AllMarketsRequest allMarketsRequest) {
        return new ResponseEntity<>(marketService.getNearbyMarkets(allMarketsRequest), HttpStatus.OK);
    }
}

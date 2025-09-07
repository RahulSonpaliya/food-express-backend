package com.example.api;

import com.example.exception.JobPortalException;
import com.example.model.request.NearbyMarketsRequest;
import com.example.model.response.MarketDetailResponse;
import com.example.model.response.NearbyMarketsResponse;
import com.example.service.MarketService;
import com.example.service.ProductService;
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

    @Autowired
    private ProductService productService;

    @PostMapping("/nearby")
    public ResponseEntity<NearbyMarketsResponse> getNearbyMarkets(@RequestBody @Valid NearbyMarketsRequest allMarketsRequest) {
        return new ResponseEntity<>(marketService.getNearbyMarkets(allMarketsRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{marketId}")
    public ResponseEntity<MarketDetailResponse> getMarketDetail(@PathVariable(value = "marketId") String marketId, @RequestParam(value = "category_id", required = false, defaultValue = "-1") String categoryId) throws JobPortalException {
        var market = marketService.getMarketById(marketId);
        var products = productService.getProductsByMarketId(marketId);
        var marketDetailResponse = new MarketDetailResponse("Success", true);
        marketDetailResponse.setMarket(market);
        marketDetailResponse.setProducts(products);
        return new ResponseEntity<>(marketDetailResponse, HttpStatus.OK);
    }
}

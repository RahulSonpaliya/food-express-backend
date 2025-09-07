package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product_options")
public class ProductOption {
    @Id
    private Long id;
    private String variantName;
    private double price;
}

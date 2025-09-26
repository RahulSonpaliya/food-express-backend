package com.example.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    @NotNull(message = "{quantity.invalid}")
    @Min(value = 1, message = "{quantity.invalid}")
    private Long quantity;

    @NotNull(message = "{productId.invalid}")
    @Min(value = 1, message = "{productId.invalid}")
    private Long productId;

    @NotNull(message = "{variantId.invalid}")
    @Min(value = 1, message = "{variantId.invalid}")
    private Long variantId;
}


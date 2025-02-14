package com.example.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NearbyMarketsRequest {
	@NotBlank(message = "{market.latitude.required}")
	private String latitude;
	@NotBlank(message = "{market.longitude.required}")
	private String longitude;
	private Long categoryId;
}

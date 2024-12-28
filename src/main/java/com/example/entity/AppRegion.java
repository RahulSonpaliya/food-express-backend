package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "app_regions")
public class AppRegion {
	private String calling;
	private String name;
	private String alpha2;
	private String alpha3;
}

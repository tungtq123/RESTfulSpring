package com.tqt.restfulspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private String description;
}

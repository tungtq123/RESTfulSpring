package com.tqt.restfulspring.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private String description;
    private Long categoryId;
}

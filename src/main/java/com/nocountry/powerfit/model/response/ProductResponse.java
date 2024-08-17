package com.nocountry.powerfit.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private Integer stock;
    private Double price;
    private String category;
    private String description;
    private List<ImageResponse> imgList;


}
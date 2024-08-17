package com.nocountry.powerfit.model.request;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PreferenceRequest {

    private Long id;

    private String name;

    private String description;

    private int quantity;

    private BigDecimal price;

    private String pictureUrl;
}

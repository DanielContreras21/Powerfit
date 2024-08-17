package com.nocountry.powerfit.model.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
    private Long id;
    private UserCartResponse user;
    private List<ProductResponse> products;
    private Double amount;
    private Integer quantity;
}

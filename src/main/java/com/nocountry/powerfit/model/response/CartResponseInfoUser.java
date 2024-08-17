package com.nocountry.powerfit.model.response;

import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.entity.User;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResponseInfoUser {

    private Long id;
    private List<ProductResponse> products;
    private Double amount;
    private String nameUser;
    private Integer quantity;
}

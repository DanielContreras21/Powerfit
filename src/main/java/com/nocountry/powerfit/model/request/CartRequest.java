package com.nocountry.powerfit.model.request;

import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.entity.User;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartRequest {
    private Long id;
    private User user;
    private List<Product> products;
    private Double amount;
    private String nameUser;
    private Integer quantity;
    //private Category category;

}

package com.nocountry.powerfit.model.response;

import com.nocountry.powerfit.model.entity.Product;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private List<Product> products;
}

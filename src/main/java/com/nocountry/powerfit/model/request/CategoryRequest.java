package com.nocountry.powerfit.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private Long id;
    private String name;
}

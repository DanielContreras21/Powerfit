package com.nocountry.powerfit.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCartResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
}

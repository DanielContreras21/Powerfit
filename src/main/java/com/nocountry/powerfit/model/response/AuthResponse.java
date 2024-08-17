package com.nocountry.powerfit.model.response;

import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String message;
    private Long cartId;
    private String name;
}

package com.nocountry.powerfit.model.response;

import com.nocountry.powerfit.model.entity.Role;
import lombok.*;

import java.util.Date;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//Agregar las clases que faltan
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String document;
    private Long postalCode;
    private Role role;
    private CartResponse cartResponse;
}

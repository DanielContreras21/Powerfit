package com.nocountry.powerfit.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String lastName;
    private String phoneNumber;
    @NotBlank(message = "Please enter an email")
    @Email(message = "Email must have a valid format")
    @NotNull(message = "Email can't be null.")
    private String email;
    private String password;
}

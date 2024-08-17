package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.entity.User;
import com.nocountry.powerfit.model.exception.AttributeException;
import com.nocountry.powerfit.model.request.LoginRequest;
import com.nocountry.powerfit.model.request.RegisterRequest;
import com.nocountry.powerfit.model.response.AuthResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {

    AuthResponse register(RegisterRequest registerRequest) throws IOException;
    AuthResponse login(LoginRequest loginRequest);
}

package com.nocountry.powerfit.service;

import com.nocountry.powerfit.model.entity.Cart;
import com.nocountry.powerfit.model.entity.Role;
import com.nocountry.powerfit.model.entity.User;
import com.nocountry.powerfit.model.exception.EntityAlreadyExistException;
import com.nocountry.powerfit.model.exception.InvalidCredentialsException;
import com.nocountry.powerfit.model.request.LoginRequest;
import com.nocountry.powerfit.model.request.RegisterRequest;
import com.nocountry.powerfit.model.response.AuthResponse;
import com.nocountry.powerfit.repository.IUserRepository;
import com.nocountry.powerfit.service.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse register(RegisterRequest registerRequest) throws IOException {
        boolean isUserExist = userRepository.existsByEmail(registerRequest.getEmail());
        if (isUserExist){
            throw new EntityAlreadyExistException("El usuario ya está registrado");
        }
        var user = User.builder()
                .name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .cart(new Cart())
                .build();
        userRepository.save(user);
        var jwToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwToken)
                .message("Usuario creado")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwToken = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .token(jwToken)
                    .cartId(user.getCart().getId())
                    .name(user.getName())
                    .build();
        }catch (Exception e){
            throw new InvalidCredentialsException("Usuario o contraseña invalidos");
        }
    }

}

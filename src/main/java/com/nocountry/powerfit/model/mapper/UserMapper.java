package com.nocountry.powerfit.model.mapper;

import com.nocountry.powerfit.model.entity.Role;
import com.nocountry.powerfit.model.entity.User;

import com.nocountry.powerfit.model.request.UserRequest;

import com.nocountry.powerfit.model.response.UserResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Builder
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CartMapper cartMapper;

    public UserResponse dtoToEntityUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .city(user.getCity())
                .address(user.getAddress())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .lastName(user.getLastName())
                .postalCode(user.getPostalCode())
                .email(user.getEmail())
                .role(Role.USER)
                .document(user.getDocument())
                .build();
    }

    public User updateToDto(UserRequest updateRequest){
        return User.builder()
                .id(updateRequest.getId())
                .password(updateRequest.getPassword())
                .email(updateRequest.getEmail())
                .name(updateRequest.getName())
                .lastName(updateRequest.getLastName())
                .postalCode(updateRequest.getPostalCode())
                .city(updateRequest.getCity())
                .address(updateRequest.getAddress())
                .phoneNumber(updateRequest.getPhoneNumber())
                .document(updateRequest.getDocument())
                .build();
    }

    public UserResponse mapToDto(Optional<User> user) {
        return UserResponse.builder()
                .id(user.get().getId())
                .email(user.get().getEmail())
                .name(user.get().getName())
                .role(user.get().getRole())
                .cartResponse(cartMapper.mapToDto(user.get().getCart()))
                .build();
    }
}

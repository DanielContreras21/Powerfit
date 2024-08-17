package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.request.UserRequest;
import com.nocountry.powerfit.model.response.UserResponse;

public interface UserService {
    UserResponse getUserById(Long id);
    UserResponse updateUser(UserRequest request);
    void deleteUser(Long id);

    UserResponse getUserInfo();
}

package com.nocountry.powerfit.controller;

import com.nocountry.powerfit.model.request.UserRequest;
import com.nocountry.powerfit.model.response.UserResponse;
import com.nocountry.powerfit.service.abstraction.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "Obtiene información del usuario", notes = "Devuelve datos del usuario")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        UserResponse response =userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Elimina un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id){
        userService.deleteUser(id);
        String response = "User deleted successfully";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Actualización de usuario", notes = "Devuelve info del usuario")
    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse response = userService.updateUser(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

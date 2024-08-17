package com.nocountry.powerfit.repository;

import com.nocountry.powerfit.model.entity.User;
import com.nocountry.powerfit.model.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);


}

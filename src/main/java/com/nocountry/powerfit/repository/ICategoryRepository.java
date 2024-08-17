package com.nocountry.powerfit.repository;

import com.nocountry.powerfit.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long>{
    boolean existsByName(String name);
    Category findByName(String name);
}

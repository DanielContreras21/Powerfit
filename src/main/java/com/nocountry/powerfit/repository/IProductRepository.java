package com.nocountry.powerfit.repository;

import com.nocountry.powerfit.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByCategory(String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:productName%")
    List<Product> findBySimilarName(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE LOWER(p.category) LIKE %:categoryName%")
    List<Product> findBySimilarCategoryName(@Param("categoryName") String categoryName);

//    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
//    List<Product> findTopNByOrderByIdDesc(Pageable pageable);


}
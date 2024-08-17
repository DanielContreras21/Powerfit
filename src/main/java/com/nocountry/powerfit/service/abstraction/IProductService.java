package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.request.ProductRequest;
import com.nocountry.powerfit.model.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IProductService {

    ProductResponse add(List<MultipartFile> postImage, ProductRequest request);
    void save(Product product);
    void delete(Long id) throws ResourceNotFoundException;
    ProductResponse getById(Long id) throws ResourceNotFoundException;
    List<ProductResponse> getAll();
    List<ProductResponse> findByName(String productName) throws ResourceNotFoundException;
    List<ProductResponse> getProductsForCategory(String categoryName) throws ResourceNotFoundException;






//    Product update(Long id, Product product);
}
package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.entity.Category;
import com.nocountry.powerfit.model.request.CategoryRequest;
import com.nocountry.powerfit.model.response.CategoryResponse;

import java.util.List;


public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    void deleteCategoryById(Long id);
    List<Category> getAllCategory();
    CategoryResponse getByName(String name);
    CategoryResponse updateCategory(CategoryRequest request);
    Category findById(Long categoryId);
}

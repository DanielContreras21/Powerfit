package com.nocountry.powerfit.model.mapper;

import com.nocountry.powerfit.model.entity.Category;
import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.request.CategoryRequest;
import com.nocountry.powerfit.model.response.CategoryResponse;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {

    public Category categoryToDto(CategoryRequest categoryRequest) {
        return Category.builder()
                .id(categoryRequest.getId())
                .name(categoryRequest.getName())
                .build();
    }

    public CategoryResponse dtoToCategory(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

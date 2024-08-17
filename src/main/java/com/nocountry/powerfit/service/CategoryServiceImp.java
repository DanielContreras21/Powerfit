package com.nocountry.powerfit.service;

import com.nocountry.powerfit.model.entity.Category;
import com.nocountry.powerfit.model.exception.EntityAlreadyExistException;
import com.nocountry.powerfit.model.exception.UserAlreadyExistException;
import com.nocountry.powerfit.model.mapper.CategoryMapper;
import com.nocountry.powerfit.model.request.CategoryRequest;
import com.nocountry.powerfit.model.response.CategoryResponse;
import com.nocountry.powerfit.repository.ICategoryRepository;
import com.nocountry.powerfit.service.abstraction.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        boolean isCategoryExist = categoryRepository.existsByName(request.getName());
        if (isCategoryExist){
            throw new EntityAlreadyExistException("La categoría ya existe");
        }
        Category createCategory = categoryRepository.save(categoryMapper.categoryToDto(request));
        CategoryResponse reponse = categoryMapper.dtoToCategory(createCategory);
        return reponse;
    }

    @Override
    public void deleteCategoryById(Long id) {
        boolean isCategoryExist = categoryRepository.existsById(id);
        if (!isCategoryExist){
            throw new EntityNotFoundException("La categoría no existe");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryResponse getByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null){
            throw new EntityNotFoundException("La categoría no existe");
        }
        return categoryMapper.dtoToCategory(category);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request) {
        boolean isCategoryExistId = categoryRepository.existsById(request.getId());
        boolean isCategoryNameExist = categoryRepository.existsByName(request.getName());
        if (!isCategoryExistId){
            throw new EntityNotFoundException("La categoría no existe");
        }else if (isCategoryNameExist){
            throw new UserAlreadyExistException("La categoría ya existe");
        }
        Category categoryUpdate = categoryMapper.categoryToDto(request);
        categoryRepository.save(categoryUpdate);
        CategoryResponse response = categoryMapper.dtoToCategory(categoryUpdate);
        return response;

    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.getReferenceById(categoryId);
    }
}

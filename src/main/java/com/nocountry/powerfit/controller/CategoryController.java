package com.nocountry.powerfit.controller;

import com.nocountry.powerfit.model.entity.Category;
import com.nocountry.powerfit.model.request.CategoryRequest;
import com.nocountry.powerfit.model.response.CategoryResponse;
import com.nocountry.powerfit.service.abstraction.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @ApiOperation(value = "Obtiene información de la categoría", notes = "Devuelve datos de la categoría")
    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponse> getByName(@Valid @PathVariable String name) {
        CategoryResponse response = categoryService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Obtiene todas las categorías", notes = "Devuelve datos de las categoría")
    @GetMapping("all")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> response = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Crea una categoría")
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Borra una categoría")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable Long id){
        String response = "Category deleted successfully";
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Actualiza una categoría")
    @PutMapping("/update")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest request){
        CategoryResponse response = categoryService.updateCategory(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}

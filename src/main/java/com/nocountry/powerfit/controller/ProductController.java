package com.nocountry.powerfit.controller;


import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.request.ProductRequest;
import com.nocountry.powerfit.model.response.ProductResponse;
import com.nocountry.powerfit.service.abstraction.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@Api(value = "Product Controller", description = "Product functionalities")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @PostMapping("/add")
    @ApiOperation(value = "Registro de un producto", notes = "Retorna producto creado")
    public ResponseEntity<ProductResponse> uploadFiles(
            @RequestParam(value = "postImages") List<MultipartFile> postImage ,
            @RequestPart(value = "product") ProductRequest request) {
        ProductResponse response = iProductService.add(postImage, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/all")
    @ApiOperation(value = "Busca todos los productos", notes = "Retorna lista de productos en stock")
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProductResponse> response = iProductService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/name/{productName}")
    @ApiOperation(value = "Busca producto por nombre", notes = "Retorna producto por nombre")
    public ResponseEntity<List<ProductResponse>> findByName (@PathVariable String productName) throws ResourceNotFoundException {
        List<ProductResponse> response = iProductService.findByName(productName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/category/{categoryName}")
    @ApiOperation(value = "Busca por categoría", notes = "Retorna lista de productos por categoría")
    public ResponseEntity<List<ProductResponse>> getProductsForCategory (@PathVariable String categoryName) throws ResourceNotFoundException {
        List<ProductResponse> response = iProductService.getProductsForCategory(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca por id", notes = "Retorna un producto")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) throws ResourceNotFoundException {
        ProductResponse response = iProductService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borra producto por id", notes = "Confirma con mensaje en el cuerpo si borró o no el id indicado")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        iProductService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Producto con el id " + id + " eliminado exitosamente");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @Validated @RequestBody Product product) {
//        Product updatedProduct = iProductService.update(id ,product);
//        if (updatedProduct == null) {
//            return ResponseEntity.notFound().build();
//        }
//        ProductResponse productResponse = new ProductResponse();
//        productResponse.setId(product.getId());
//        productResponse.setName(product.getName());
//        productResponse.setDescription(product.getDescription());
//        productResponse.setPrice(product.getPrice());
//        productResponse.setCategory(product.getCategory().toString());
//        productResponse.setStock(true);
//
//        iProductService.save(productResponse);
//
//        return ResponseEntity.ok(productResponse);
//
//    }

}
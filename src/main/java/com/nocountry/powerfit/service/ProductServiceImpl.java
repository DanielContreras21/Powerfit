package com.nocountry.powerfit.service;

import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.exception.ApiExceptionHandler;
import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.mapper.ProductMapper;
import com.nocountry.powerfit.model.request.ProductRequest;
import com.nocountry.powerfit.model.response.ProductResponse;
import com.nocountry.powerfit.model.response.UserResponse;
import com.nocountry.powerfit.repository.IProductRepository;
import com.nocountry.powerfit.service.abstraction.CategoryService;
import com.nocountry.powerfit.service.abstraction.IImageService;
import com.nocountry.powerfit.service.abstraction.IProductService;
import com.nocountry.powerfit.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements IProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IProductRepository IProductRepository;
    @Autowired
    private IImageService imageService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public ProductResponse add(List<MultipartFile> postImage, ProductRequest request) {
        try {
            /*new product*/
            Product product = productMapper.mapToDto(request);
            product.setCarrousel(imageService.imagesPost(postImage));
            //product.setCategory(categoryService.getByName(request.getCategory()).toString());
            // add image
            Product pCreated = IProductRepository.save(product);
            return productMapper.entityToDto(pCreated);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error al agregar el producto");
        }


    }

    @Override
    public void save(Product product) {
        IProductRepository.save(product);
    }

    public List<ProductResponse> getProductsForCategory(String categoryName) throws ResourceNotFoundException {
        List<Product> products = IProductRepository.findByCategory(categoryName);
        if(products.isEmpty()) {
            products = IProductRepository.findBySimilarCategoryName(categoryName);
            if (products.isEmpty()) {
                throw new ResourceNotFoundException("No se encontró la categoría con el nombre " + categoryName);
            }
        }

        List<ProductResponse> productResponses = products.stream()
                .map(product -> productMapper.entityToDto(product))
                .collect(Collectors.toList());

        return productResponses;
    }

    @Override
    public ProductResponse getById(Long id) throws ResourceNotFoundException {
        Product product = IProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el id: " + id));
        return productMapper.entityToDto(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return IProductRepository.findAll().stream().filter(p -> p.getStock() != 0).map(productMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Product product = IProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el id " + id));
        IProductRepository.deleteById(id);
    }

    public List<ProductResponse> findByName (String productName) throws ResourceNotFoundException {
        List<Product> products = IProductRepository.findByName(productName);
        if(products.isEmpty()) {
            products = IProductRepository.findBySimilarName(productName);
            if (products.isEmpty()) {
                throw new ResourceNotFoundException("No se encontró el producto con el nombre " + productName);
            }
        }

        List<ProductResponse> productResponses = products.stream()
                .map(product -> productMapper.entityToDto(product))
                .collect(Collectors.toList());

        return productResponses;
    }

}

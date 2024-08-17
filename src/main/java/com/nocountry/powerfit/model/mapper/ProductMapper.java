package com.nocountry.powerfit.model.mapper;

import com.nocountry.powerfit.model.entity.Image;
import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.request.ProductRequest;
import com.nocountry.powerfit.model.response.ImageResponse;
import com.nocountry.powerfit.model.response.ProductResponse;
import com.nocountry.powerfit.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductMapper {

    @Autowired
    private ImageMapper imageMapper;

    public ProductResponse entityToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .category(product.getCategory())
                .imgList(product.getCarrousel().stream()
                        .map(imageMapper::imageToDto)
                        .collect(Collectors.toList()))
                .build();


    }

    public Product dtoToProduct(ProductRequest request, UserResponse user) {
        return Product.builder()
                .description(request.getDescription())
                .name(request.getName())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    public List<ProductResponse> entityToDtoList(List<Product> products) {
        List<ProductResponse> responses = new ArrayList<>();
        ProductResponse productResponse;
        for (Product p: products){
            productResponse = new ProductResponse();
            productResponse.setId(p.getId());
            productResponse.setName(p.getName());
            productResponse.setDescription(p.getDescription());
            productResponse.setCategory(p.getCategory());
            productResponse.setPrice(p.getPrice());
            productResponse.setStock(p.getStock());
            productResponse.setImgList(p.getCarrousel().stream().map(
                            imageMapper::imageToDto)
                    .collect(Collectors.toList()));
            responses.add(productResponse);
        }
        return responses;
    }

    public Product mapToDto(ProductRequest request) {
        return Product.builder()
                .stock(request.getStock())
                .price(request.getPrice())
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .build();
    }
}
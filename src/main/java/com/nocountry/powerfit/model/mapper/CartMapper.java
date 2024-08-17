package com.nocountry.powerfit.model.mapper;

import com.nocountry.powerfit.model.entity.Cart;
import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.entity.User;
import com.nocountry.powerfit.model.request.CartRequest;
import com.nocountry.powerfit.model.response.CartResponse;
import com.nocountry.powerfit.model.response.ProductResponse;
import com.nocountry.powerfit.model.response.UserCartResponse;
import com.nocountry.powerfit.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CartMapper {
    private final ImageMapper imageMapper;
    public  CartResponse entityToDto(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .user(mapUserToDto(cart.getUser()))
                .products(mapToDtoProduct(cart.getProducts()))
                .amount(cart.getAmount())
                .quantity(cart.getQuantity())
                .build();
    }

    public static Cart dtoToEntity(CartRequest cartRequest) {
        return Cart.builder()
                .id(cartRequest.getId())
               // .user(cartRequest.getUser())
                .products(cartRequest.getProducts())
                .amount(cartRequest.getAmount())
                .quantity(cartRequest.getQuantity())

                .build();
    }

    public CartResponse mapToDto(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .amount(cart.getAmount())
                .products(mapToDtoProduct(cart.getProducts()))
                .build();
    }

    private List<ProductResponse> mapToDtoProduct(List<Product> products) {
        return products.stream()
                .map(this::mapProductToProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setImgList(imageMapper.mapToDtoImagesList(product.getCarrousel()));
        return response;
    }

    public UserCartResponse mapUserToDto(User user){
        return UserCartResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}

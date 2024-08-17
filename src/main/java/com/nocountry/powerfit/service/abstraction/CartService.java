package com.nocountry.powerfit.service.abstraction;

import com.nocountry.powerfit.model.exception.CartNotFoundException;
import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.response.CartResponse;

import java.util.List;

public interface CartService {

    CartResponse addProduct(Long cartId, Long productId) throws ResourceNotFoundException, CartNotFoundException;
    void removeProductFromCart(Long cartId, Long productId) throws ResourceNotFoundException, CartNotFoundException;
    CartResponse getCartById(Long cartId) throws CartNotFoundException, ResourceNotFoundException;
    void clearCart(Long cartId) throws CartNotFoundException;

    //  CartResponse updateProductQuantity(Long cartId, Long productId, int stock) throws ResourceNotFoundException, CartNotFoundException;
}

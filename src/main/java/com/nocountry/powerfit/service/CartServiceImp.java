package com.nocountry.powerfit.service;

import com.nocountry.powerfit.model.entity.Cart;
import com.nocountry.powerfit.model.entity.Product;
import com.nocountry.powerfit.model.exception.CartNotFoundException;
import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.mapper.CartMapper;
import com.nocountry.powerfit.model.response.CartResponse;
import com.nocountry.powerfit.repository.ICartRepository;
import com.nocountry.powerfit.repository.IProductRepository;
import com.nocountry.powerfit.service.abstraction.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartMapper cartMapper;
    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;

    @Override
    public CartResponse addProduct(Long cartId, Long productId) throws CartNotFoundException, ResourceNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Carrito no encontrado"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        cart.addProduct(product);
        cartRepository.save(cart);

        return cartMapper.entityToDto(cart);
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId) throws ResourceNotFoundException, CartNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Carrito no encontrado"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        if (!cart.getProducts().contains(product)) {
            throw new ResourceNotFoundException("Producto no encontrado en el carrito");
        }

        cart.removeProduct(product);
        cartRepository.save(cart);

    }

    @Override
    public CartResponse getCartById(Long cartId) throws CartNotFoundException, ResourceNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Carrito no encontrado"));

        if(cart.getProducts().isEmpty()){
            throw new ResourceNotFoundException("El carrito está vacío");        }

        CartResponse cartResponse = cartMapper.entityToDto(cart);

        return cartResponse;
    }

    @Override
    public void clearCart(Long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException ("Carrito no encontrado"));

        if (cart.getAmount() == 0 && cart.getQuantity() == 0 && cart.getProducts().isEmpty()){
            throw new IllegalStateException("El carrito ya está vacío");
        }

        cart.getProducts().clear();
        cart.setAmount(0D);
        cart.setQuantity(0);

        cartRepository.save(cart);
    }

    //    @Override
//    public CartResponse updateProductQuantity(Long cartId, Long productId, int stock) throws ResourceNotFoundException, CartNotFoundException {
//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new CartNotFoundException("Carrito no encontrado"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
//
//        if (!cart.getProducts().contains(product)) {
//            throw new ResourceNotFoundException("Producto no encontrado en el carrito");
//        }
//
//        product.setStock(stock);
//
//        cartRepository.save(cart);
//
//        return CartMapper.entityToDto(cart);
//    }

}


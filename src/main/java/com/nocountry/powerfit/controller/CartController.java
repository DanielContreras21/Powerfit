package com.nocountry.powerfit.controller;

import com.nocountry.powerfit.model.exception.CartNotFoundException;
import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.response.CartResponse;
import com.nocountry.powerfit.service.abstraction.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Api(value = "Cart Controller")
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @PutMapping("/{cartId}/products")
    @ApiOperation(value = "Agrega producto al carrito existente")
    public ResponseEntity<String> addProductsToCart(@PathVariable Long cartId, @RequestParam Long productId) {
        try {
            cartService.addProduct(cartId, productId);
            return ResponseEntity.ok().body("{\"message\": \"Producto agregado al carrito exitosamente\"}");
        } catch (ResourceNotFoundException | CartNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cartId}/products")
    @ApiOperation(value = "Obtiene carrito por id con todos sus productos")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long cartId) throws CartNotFoundException, ResourceNotFoundException {
        return ResponseEntity.ok().body(cartService.getCartById(cartId));
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    @ApiOperation(value = "Elimina producto del carrito")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        try {
            cartService.removeProductFromCart(cartId, productId);
            return ResponseEntity.ok().body("{\"message\": \"Producto eliminado exitosamente\"}");
        } catch (ResourceNotFoundException | CartNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clear/{cartId}")
    @ApiOperation(value = "Vacía el carrito")
    public ResponseEntity<?> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok().body("Se ha vaciado el carrito exitosamente");
        } catch (CartNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //    @PutMapping("/{cartId}/product/{productId}")
//    @ApiOperation(value = "Modifica la cantidad del producto en el carrito")
//    public ResponseEntity<Void> updateProductQuantity(
//            @PathVariable Long cartId,
//            @PathVariable Long productId,
//            @RequestParam Integer quantity) {
//        try {
//            cartService.updateProductQuantity(cartId, productId, quantity);
//            return ResponseEntity.noContent().build();
//        } catch (ResourceNotFoundException | CartNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

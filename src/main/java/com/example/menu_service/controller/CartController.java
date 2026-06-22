package com.example.menu_service.controller;

import com.example.menu_service.dto.CartRequest;
import com.example.menu_service.entity.Cart;
import com.example.menu_service.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService service;

    public CartController(
            CartService service) {

        this.service = service;
    }

    // Add Product To Cart
    @PostMapping("/add/{userId}/{vendorId}")
    public Cart addToCart(
            @PathVariable Long userId,
            @PathVariable Long vendorId,
            @RequestBody CartRequest request) {

        return service.addToCart(
                userId,
                vendorId,
                request);
    }

    // Get Cart By User
    @GetMapping("/user/{userId}")
    public List<Cart> getCart(
            @PathVariable Long userId) {

        return service.getCart(userId);
    }

    // Get Cart By Cart Id
    @GetMapping("/by-id/{cartId}")
    public Cart getCartById(
            @PathVariable Long cartId) {

        return service.getCartById(cartId);
    }

    // Place Order
    @PostMapping("/place-order/{cartId}")
    public String placeOrder(
            @PathVariable Long cartId) {

        return service.placeOrder(cartId);
    }

    // Delete Cart Item
    @DeleteMapping("/{cartId}")
    public String deleteCart(
            @PathVariable Long cartId) {

        service.deleteCart(cartId);

        return "Cart Item Deleted Successfully";
    }
}
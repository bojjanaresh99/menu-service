package com.example.menu_service.service;

import com.example.menu_service.dto.CartRequest;
import com.example.menu_service.dto.OrderEvent;
import com.example.menu_service.entity.Cart;
import com.example.menu_service.entity.Product;
import com.example.menu_service.repository.CartRepository;
import com.example.menu_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final KafkaProducerService kafkaProducerService;
    private final RestTemplate restTemplate;

    public CartService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            KafkaProducerService kafkaProducerService,
            RestTemplate restTemplate) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.restTemplate = restTemplate;
    }

    // Add Product To Cart
    public Cart addToCart(
            Long userId,
            Long vendorId,
            CartRequest request) {

        // Validate User
        String userUrl =
                "http://localhost:8081/user/" + userId;

        Object user =
                restTemplate.getForObject(
                        userUrl,
                        Object.class);

        if (user == null) {

            throw new RuntimeException(
                    "User Not Found");
        }

        // Validate Vendor
        String vendorUrl =
                "http://localhost:8081/vendor-enquiry/" + vendorId;

        Object vendor =
                restTemplate.getForObject(
                        vendorUrl,
                        Object.class);

        if (vendor == null) {

            throw new RuntimeException(
                    "Vendor Not Found");
        }

        // Validate Product
        Product product =
                productRepository.findById(
                                request.getProductId())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Product Not Found"));

        Cart cart = new Cart();

        cart.setUserId(userId);

        cart.setVendorId(vendorId);

        cart.setProductId(
                product.getProductId());

        cart.setProductName(
                product.getProductName());

        cart.setPrice(
                product.getPrice());

        cart.setQuantity(
                request.getQuantity());

        cart.setTotalPrice(
                product.getPrice()
                        * request.getQuantity());

        cart.setImageUrl(
                product.getImageUrl());

        return cartRepository.save(
                cart);
    }

    // Get Cart By User
    public List<Cart> getCart(
            Long userId) {

        return cartRepository.findByUserId(
                userId);
    }

    // Get Cart By Id
    public Cart getCartById(
            Long cartId) {

        return cartRepository.findById(
                        cartId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Cart Not Found"));
    }

    // Place Order
    public String placeOrder(
            Long cartId) {

        Cart cart =
                cartRepository.findById(
                                cartId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Cart Not Found"));

        OrderEvent event =
                new OrderEvent();

        event.setCartId(
                cart.getCartId());

        event.setVendorId(
                cart.getVendorId());

        event.setProductId(
                cart.getProductId());

        event.setProductName(
                cart.getProductName());

        event.setPrice(
                cart.getPrice());

        event.setQuantity(
                cart.getQuantity());

        event.setTotalPrice(
                cart.getTotalPrice());

        kafkaProducerService.sendOrder(
                event);

        return "Order Sent Successfully";
    }

    // Delete Cart
    public void deleteCart(
            Long cartId) {

        Cart cart =
                cartRepository.findById(
                                cartId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Cart Not Found"));

        cartRepository.delete(
                cart);
    }
}
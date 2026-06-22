package com.example.menu_service.controller;

import com.example.menu_service.dto.ProductRequest;
import com.example.menu_service.entity.Product;
import com.example.menu_service.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

    @Autowired
    private MenuService service;

    @Operation(
            summary = "Create Grocery Product"
    )
    @PostMapping("/create-product/{vendorId}")
    public Product createProduct(

            @PathVariable Long vendorId,

            @RequestBody ProductRequest request) {

        return service.createProduct(
                vendorId,
                request
        );
    }

    @Operation(
            summary = "Get Child Products"
    )
    @GetMapping("/children/{parentId}")
    public List<Product> getChildren(
            @PathVariable Long parentId) {

        return service.getChildren(parentId);
    }

    @Operation(
            summary = "Get Vendor Products"
    )
    @GetMapping("/vendor/{vendorId}")
    public List<Product> getVendorProducts(
            @PathVariable Long vendorId) {

        return service.getVendorProducts(vendorId);
    }
}
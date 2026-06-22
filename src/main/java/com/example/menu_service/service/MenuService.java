package com.example.menu_service.service;

import com.example.menu_service.dto.ProductRequest;
import com.example.menu_service.entity.Product;
import com.example.menu_service.repository.ProductRepository;
import com.example.menu_service.security.JwtContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private ProductRepository repository;

    public Product createProduct(
            Long vendorId,
            ProductRequest request) {

        // Vendor Id from JWT Token
        Long jwtVendorId =
                JwtContext.getVendorId();

        if (jwtVendorId == null) {

            throw new RuntimeException(
                    "Vendor not authenticated"
            );
        }

        // Validate Vendor
        if (!jwtVendorId.equals(vendorId)) {

            throw new RuntimeException(
                    "Unauthorized Vendor Access"
            );
        }

        Product product = new Product();

        product.setVendorId(vendorId);

        product.setParentId(
                request.getParentId()
        );

        product.setProductName(
                request.getProductName()
        );

        product.setPrice(
                request.getPrice()
        );

        product.setImageUrl(
                request.getImageUrl()
        );

        product.setStatus("ACTIVE");

        return repository.save(product);
    }

    public List<Product> getChildren(
            Long parentId) {

        return repository.findByParentId(
                parentId
        );
    }

    public List<Product> getVendorProducts(
            Long vendorId) {

        return repository.findByVendorId(
                vendorId
        );
    }
}
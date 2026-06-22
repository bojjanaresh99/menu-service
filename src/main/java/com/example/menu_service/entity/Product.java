package com.example.menu_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long productId;

    private Long vendorId;

    private Long parentId;

    private String productName;

    private Double price;

    private String imageUrl;

    private String status;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(
            Long productId) {

        this.productId = productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(
            Long vendorId) {

        this.vendorId = vendorId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(
            Long parentId) {

        this.parentId = parentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(
            String productName) {

        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(
            Double price) {

        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(
            String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }
}
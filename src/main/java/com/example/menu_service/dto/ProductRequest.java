package com.example.menu_service.dto;

public class ProductRequest {

    private Long parentId;

    private String productName;

    private Double price;

    private String imageUrl;

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
}
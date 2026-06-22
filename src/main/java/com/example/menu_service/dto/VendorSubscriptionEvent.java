package com.example.menu_service.dto;

public class VendorSubscriptionEvent {

    private Long vendorId;

    private String planName;

    private String status;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(
            Long vendorId) {

        this.vendorId = vendorId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(
            String planName) {

        this.planName = planName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }
}
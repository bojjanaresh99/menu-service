package com.example.menu_service.security;

public class JwtContext {

    private static final ThreadLocal<Long>
            vendorHolder =
            new ThreadLocal<>();

    public static void setVendorId(
            Long vendorId) {

        vendorHolder.set(vendorId);
    }

    public static Long getVendorId() {

        return vendorHolder.get();
    }

    public static void clear() {

        vendorHolder.remove();
    }
}
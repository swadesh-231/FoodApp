package com.foodapp.constants;

public final class AppConstants {

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String DELIVERY_PARTNER = "DELIVERY_PARTNER";
    public static final String RESTAURANT = "RESTAURANT";

    public static final String ROLE_ADMIN = ROLE_PREFIX + ADMIN;
    public static final String ROLE_USER = ROLE_PREFIX + USER;
    public static final String ROLE_DELIVERY_PARTNER = ROLE_PREFIX + DELIVERY_PARTNER;
    public static final String ROLE_RESTAURANT = ROLE_PREFIX + RESTAURANT;

    private AppConstants() {
    }
}

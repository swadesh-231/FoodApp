package com.foodapp.service;

import com.foodapp.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getActiveRestaurants();

    RestaurantResponse getRestaurantById(Long restaurantId);
}

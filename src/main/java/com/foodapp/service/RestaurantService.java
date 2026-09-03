package com.foodapp.service;

import com.foodapp.dto.request.CreateRestaurantRequest;
import com.foodapp.dto.request.UpdateRestaurantRequest;
import com.foodapp.dto.response.RestaurantResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getActiveRestaurants();

    RestaurantResponse getRestaurantById(Long restaurantId);

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse createRestaurant(CreateRestaurantRequest createRestaurantRequest);

    RestaurantResponse updateRestaurant(Long restaurantId, UpdateRestaurantRequest updateRestaurantRequest);

    void deleteRestaurant(Long restaurantId);

    RestaurantResponse updateOpenStatus(Long restaurantId, boolean open);

    RestaurantResponse uploadBanner(Long restaurantId, MultipartFile image);
}

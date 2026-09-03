package com.foodapp.service.impl;

import com.foodapp.dto.request.CreateRestaurantRequest;
import com.foodapp.dto.request.UpdateRestaurantRequest;
import com.foodapp.dto.response.RestaurantResponse;
import com.foodapp.exception.ResourceNotFoundException;
import com.foodapp.mapper.RestaurantMapper;
import com.foodapp.repository.RestaurantRepository;
import com.foodapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponse> getActiveRestaurants() {
        return restaurantRepository.findByIsActiveTrue()
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantResponse getRestaurantById(Long restaurantId) {
        return restaurantRepository.findByIdAndIsActiveTrue(restaurantId)
                .map(restaurantMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        throw new UnsupportedOperationException("getAllRestaurants is not implemented yet");
    }

    @Override
    public RestaurantResponse createRestaurant(CreateRestaurantRequest createRestaurantRequest) {
        throw new UnsupportedOperationException("createRestaurant is not implemented yet");
    }

    @Override
    public RestaurantResponse updateRestaurant(Long restaurantId, UpdateRestaurantRequest updateRestaurantRequest) {
        throw new UnsupportedOperationException("updateRestaurant is not implemented yet");
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        throw new UnsupportedOperationException("deleteRestaurant is not implemented yet");
    }

    @Override
    public RestaurantResponse updateOpenStatus(Long restaurantId, boolean open) {
        throw new UnsupportedOperationException("updateOpenStatus is not implemented yet");
    }

    @Override
    public RestaurantResponse uploadBanner(Long restaurantId, MultipartFile image) {
        throw new UnsupportedOperationException("uploadBanner is not implemented yet");
    }
}

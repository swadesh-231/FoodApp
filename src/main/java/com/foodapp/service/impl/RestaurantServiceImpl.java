package com.foodapp.service.impl;

import com.foodapp.dto.response.RestaurantResponse;
import com.foodapp.exception.ResourceNotFoundException;
import com.foodapp.mapper.RestaurantMapper;
import com.foodapp.repository.RestaurantRepository;
import com.foodapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

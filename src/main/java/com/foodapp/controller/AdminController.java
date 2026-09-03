package com.foodapp.controller;

import com.foodapp.dto.request.CreateRestaurantRequest;
import com.foodapp.dto.request.UpdateRestaurantRequest;
import com.foodapp.dto.response.AddressResponse;
import com.foodapp.dto.response.RestaurantResponse;
import com.foodapp.service.AddressService;
import com.foodapp.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantResponse> createRestaurant(@Valid @RequestBody CreateRestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.createRestaurant(request));
    }
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponse>> getRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId));
    }

    @PatchMapping("/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@PathVariable Long restaurantId, @Valid @RequestBody UpdateRestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, request));
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/restaurants/{restaurantId}/open")
    public ResponseEntity<RestaurantResponse> updateRestaurantOpenStatus(
            @PathVariable Long restaurantId,
            @RequestParam boolean open
    ) {
        return ResponseEntity.ok(restaurantService.updateOpenStatus(restaurantId, open));
    }

    @PostMapping(
            value = "/restaurants/{restaurantId}/banner",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<RestaurantResponse> uploadRestaurantBanner(
            @PathVariable Long restaurantId,
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(
                restaurantService.uploadBanner(
                        restaurantId,
                        image
                )
        );
    }
}

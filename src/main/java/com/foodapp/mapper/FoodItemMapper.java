package com.foodapp.mapper;

import com.foodapp.dto.response.FoodItemResponse;
import com.foodapp.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FoodItemMapper {

    @Mapping(target = "restaurantId", source = "restaurant.id")
    @Mapping(target = "actualPrice", expression = "java(foodItem.actualPrice())")
    FoodItemResponse toResponse(FoodItem foodItem);
}

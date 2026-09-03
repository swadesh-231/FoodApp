package com.foodapp.mapper;

import com.foodapp.dto.response.RestaurantResponse;
import com.foodapp.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {AddressMapper.class, FoodItemMapper.class}
)
public interface RestaurantMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    // Lombok generates isActive() for the isActive field, so JavaBeans calls it "active".
    @Mapping(target = "isActive", source = "active")
    RestaurantResponse toResponse(Restaurant restaurant);
}

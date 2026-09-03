package com.foodapp.mapper;

import com.foodapp.dto.request.AddressRequest;
import com.foodapp.dto.response.AddressResponse;
import com.foodapp.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {
    @Mapping(target = "isDefault", source = "default")
    AddressResponse toResponse(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address toEntity(AddressRequest addressRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "default", source = "isDefault")
    void updateEntity(AddressRequest addressRequest, @MappingTarget Address address);
}

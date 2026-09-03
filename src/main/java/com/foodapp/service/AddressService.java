package com.foodapp.service;

import com.foodapp.dto.request.AddressRequest;
import com.foodapp.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse createAddress(AddressRequest addressRequest);

    List<AddressResponse> getAddresses();

    AddressResponse getAddressById(Long addressId);

    List<AddressResponse> getUserAddresses();

    AddressResponse updateAddress(Long addressId, AddressRequest addressRequest);

    AddressResponse setDefaultAddress(Long addressId);

    void deleteAddress(Long addressId);
}

package com.foodapp.service.impl;

import com.foodapp.dto.request.AddressRequest;
import com.foodapp.dto.response.AddressResponse;
import com.foodapp.entity.Address;
import com.foodapp.entity.User;
import com.foodapp.exception.ResourceNotFoundException;
import com.foodapp.lib.AuthUtil;
import com.foodapp.mapper.AddressMapper;
import com.foodapp.repository.AddressRepository;
import com.foodapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final AuthUtil authUtil;

    @Override
    @Transactional
    public AddressResponse createAddress(AddressRequest addressRequest) {
        User user = authUtil.getUser();
        Address address = addressMapper.toEntity(addressRequest);
        address.setUser(user);
        if (addressRequest.isDefault()) {
            clearExistingDefault(user);
        }
        return addressMapper.toResponse(addressRepository.save(address));
    }

    @Override
    public List<AddressResponse> getAddresses() {
        return addressRepository.findAllByDeletedFalse()
                .stream()
                .map(addressMapper::toResponse)
                .toList();
    }

    @Override
    public AddressResponse getAddressById(Long addressId) {
        return addressMapper.toResponse(findOwnedAddress(addressId));
    }

    @Override
    public List<AddressResponse> getUserAddresses() {
        return addressRepository.findByUserAndDeletedFalse(authUtil.getUser())
                .stream()
                .map(addressMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(Long addressId, AddressRequest addressRequest) {
        Address address = findOwnedAddress(addressId);
        if (addressRequest.isDefault() && !address.isDefault()) {
            clearExistingDefault(address.getUser());
        }
        addressMapper.updateEntity(addressRequest, address);
        return addressMapper.toResponse(addressRepository.save(address));
    }

    @Override
    @Transactional
    public AddressResponse setDefaultAddress(Long addressId) {
        Address address = findOwnedAddress(addressId);
        if (address.isDefault()) {
            return addressMapper.toResponse(address);
        }
        clearExistingDefault(address.getUser());
        address.setDefault(true);
        return addressMapper.toResponse(addressRepository.save(address));
    }

    @Override
    @Transactional
    public void deleteAddress(Long addressId) {
        Address address = findOwnedAddress(addressId);
        address.setDeleted(true);
        address.setDefault(false);
        addressRepository.save(address);
    }

    private Address findOwnedAddress(Long addressId) {
        Address address = addressRepository.findByIdAndDeletedFalse(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        User owner = address.getUser();
        if (owner == null || !Objects.equals(owner.getId(), authUtil.getUserId())) {
            throw new ResourceNotFoundException("Address", "id", addressId);
        }
        return address;
    }

    private void clearExistingDefault(User user) {
        if (user == null) {
            return;
        }
        addressRepository.findByUserAndDeletedFalse(user)
                .stream()
                .filter(Address::isDefault)
                .forEach(existing -> existing.setDefault(false));
    }
}

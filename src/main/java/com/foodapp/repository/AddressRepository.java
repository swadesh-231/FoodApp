package com.foodapp.repository;

import com.foodapp.entity.Address;
import com.foodapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserAndDeletedFalse(User user);

    List<Address> findAllByDeletedFalse();

    Optional<Address> findByIdAndDeletedFalse(Long addressId);
}

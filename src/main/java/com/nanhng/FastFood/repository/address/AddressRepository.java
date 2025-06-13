package com.nanhng.FastFood.repository.address;

import com.nanhng.FastFood.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> , AddressRepositoryCustom {
    List<Address> findByUserId(Integer userId);
}

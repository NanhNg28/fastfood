package com.nanhng.FastFood.service.repository.address;

import com.nanhng.FastFood.entity.address.Address;

public interface AddressRepositoryCustom {
    Address findByIdToUpdate(Integer id);
}

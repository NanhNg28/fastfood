package com.nanhng.FastFood.repository.address;

import com.nanhng.FastFood.entity.address.Address;

public interface AddressRepositoryCustom {
    Address findByIdToUpdate(Integer id);
    Address addNew (Address address);
}

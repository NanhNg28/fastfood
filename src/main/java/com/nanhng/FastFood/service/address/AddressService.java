package com.nanhng.FastFood.service.address;

import com.nanhng.FastFood.dto.request.address.AddAddressReq;
import com.nanhng.FastFood.dto.request.address.UpdateAddressRequest;
import com.nanhng.FastFood.entity.address.Address;

import java.util.List;

public interface AddressService {
    Address updateAddress(UpdateAddressRequest request);
    Integer deleteAddress(Integer addressId);
    Address addAddress(AddAddressReq request);
    List<Address> getList();
}

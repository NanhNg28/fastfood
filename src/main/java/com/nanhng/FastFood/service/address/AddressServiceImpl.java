package com.nanhng.FastFood.service.address;

import com.nanhng.FastFood.dto.request.address.UpdateAddressRequest;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.address.AddressRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends BaseService implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address updateAddress(UpdateAddressRequest request) {
        Address address = addressRepository.findById(request.getId()).orElseThrow(()->new LovelyException("Address not found", HttpStatus.BAD_REQUEST));
        if(!request.getCity().isBlank()){
            address.setCity(request.getCity());
        }
        if(!request.getStreet().isBlank()){
            address.setStreet(request.getStreet());
        }
        if(request.getStatus()!= null){
            address.setStatus(request.getStatus());
        }
        return addressRepository.save(address);
    }

    @Override
    public Integer deleteAddress(Integer addressId) {
        if(addressRepository.findById(addressId).isEmpty()){
            throw new LovelyException("Address not found", HttpStatus.BAD_REQUEST);
        }
        addressRepository.deleteById(addressId);
        return addressId;
    }
}

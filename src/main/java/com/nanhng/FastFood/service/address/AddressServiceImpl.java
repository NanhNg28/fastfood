package com.nanhng.FastFood.service.address;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.address.AddAddressReq;
import com.nanhng.FastFood.dto.request.address.UpdateAddressRequest;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.address.AddressRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends BaseService implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address updateAddress(UpdateAddressRequest request) {
        User user = getUser(RoleType.CUSTOMER);
        Address address = addressRepository.findByIdToUpdate(request.getId());
        if(address == null){
            throw new LovelyException("Không tìm thấy địa chỉ", HttpStatus.BAD_REQUEST);
        }
        if(address.getUserId()!=user.getId()){
            throw new LovelyException("<UNK>", HttpStatus.UNAUTHORIZED);
        }
        if(request.getCity() !=null &&!request.getCity().isBlank()){
            address.setCity(request.getCity());
        }
        if(request.getStreet() !=null &&!request.getStreet().isBlank()){
            address.setStreet(request.getStreet());
        }
        return addressRepository.save(address);
    }

    @Override
    public Integer deleteAddress(Integer addressId) {
        if(!addressRepository.existsById(addressId)){
            throw new LovelyException("Address not found", HttpStatus.BAD_REQUEST);
        }
        addressRepository.deleteById(addressId);
        return addressId;
    }

    @Override
    public Address addAddress(AddAddressReq request) {
        User user = getUser(RoleType.CUSTOMER);
        if(addressRepository.findByUserId(user.getId()).size() >6){
            throw new LovelyException("Tối đa 6 địa chỉ được tồn tại", HttpStatus.BAD_REQUEST);
        }

        Address address = Address.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .userId(user.getId())
                .build();
        return addressRepository.addNew(address);
    }
}

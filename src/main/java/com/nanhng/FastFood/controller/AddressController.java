package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.request.address.AddAddressReq;
import com.nanhng.FastFood.dto.request.address.UpdateAddressRequest;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.service.address.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "update exist address")//done
    @PostMapping("v1/address/update") //done
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody UpdateAddressRequest request) {
        return ResponseEntity.ok(addressService.updateAddress(request));
    }

    @Operation(summary = "delete address")//done
    @DeleteMapping("v1/address/delete/{id}")
    public ResponseEntity<Integer> deleteAddress(@PathVariable("id") Integer addressId) {
        return ResponseEntity.ok(addressService.deleteAddress(addressId));
    }

    @Operation(summary = "user add address")
    @PostMapping("v1/address/add")
    public ResponseEntity<Address> addAddress(@Valid @RequestBody AddAddressReq request) {
        return ResponseEntity.ok(addressService.addAddress(request));
    }

}

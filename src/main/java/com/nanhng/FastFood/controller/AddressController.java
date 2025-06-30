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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "update my address")//done
    @PostMapping("v1/address/update") //done
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody UpdateAddressRequest request) {
        return ResponseEntity.ok(addressService.updateAddress(request));
    }

    @Operation(summary = "delete my address")//done
    @DeleteMapping("v1/address/delete/{id}")
    public ResponseEntity<Integer> deleteAddress(@PathVariable("id") Integer addressId) {
        return ResponseEntity.ok(addressService.deleteAddress(addressId));
    }

    @Operation(summary = "user add address")
    @PostMapping("v1/address/add")
    public ResponseEntity<Address> addAddress(@Valid @RequestBody AddAddressReq request) {
        return ResponseEntity.ok(addressService.addAddress(request));
    }

    @Operation(summary = "user get my list address")
    @GetMapping("v1/address/get")
    public ResponseEntity<List<Address>> getListAddress() {
        return ResponseEntity.ok(addressService.getList());
    }

}

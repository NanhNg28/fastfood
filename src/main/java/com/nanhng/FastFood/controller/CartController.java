package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.request.cart.AddCartReq;
import com.nanhng.FastFood.dto.request.cart.CartDetailReq;
import com.nanhng.FastFood.entity.cart.Cart;
import com.nanhng.FastFood.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "add new cart")
    @PostMapping("v1/cart/add")
    public ResponseEntity<Cart> addNewCart(@Valid @RequestBody AddCartReq request){
        return ResponseEntity.ok(cartService.addCart(request));
    }

    @Operation(summary = "get list cart item")
    @GetMapping("v1/cart/get")
    public ResponseEntity<Cart> getCart(){
        return ResponseEntity.ok(cartService.getCart());
    }

}

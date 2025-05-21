package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.request.cartItem.AddCartItemReq;
import com.nanhng.FastFood.dto.request.cartItem.UpdateCartItemReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.cart.CartItem;
import com.nanhng.FastFood.service.cartItem.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CartItemController {

    private final CartItemService cartItemService;

    @Operation(description = "add new item into cart")
    @PostMapping("v1/cart-item/add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody AddCartItemReq request) {
        return ResponseEntity.ok(cartItemService.addCartItem(request));
    }

    @Operation(description = "update cart item")
    @PostMapping("v1/cart-item/update")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartItemReq request) {
        return ResponseEntity.ok(cartItemService.updateCartItem(request));
    }

    @Operation(description = "delete cart item")
    @DeleteMapping(path = "v1/cart-item/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteFood(@Valid @RequestBody IdsRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(cartItemService.deleteCartItemByIds(request),"delete successfully"));
    }

}

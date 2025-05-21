package com.nanhng.FastFood.service.cartItem;

import com.nanhng.FastFood.dto.request.cartItem.AddCartItemReq;
import com.nanhng.FastFood.dto.request.cartItem.UpdateCartItemReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.entity.cart.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addCartItem(AddCartItemReq request);
    CartItem updateCartItem(UpdateCartItemReq request);
    List<Integer> deleteCartItemByIds(IdsRequest request);
}

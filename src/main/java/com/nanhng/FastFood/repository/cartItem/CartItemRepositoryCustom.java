package com.nanhng.FastFood.repository.cartItem;

import com.nanhng.FastFood.entity.cart.CartItem;

import java.util.List;

public interface CartItemRepositoryCustom {
    List<CartItem> getAllByCartId(Integer cartId);
}

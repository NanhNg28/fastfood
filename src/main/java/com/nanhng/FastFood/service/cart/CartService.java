package com.nanhng.FastFood.service.cart;

import com.nanhng.FastFood.dto.request.cart.AddCartReq;
import com.nanhng.FastFood.dto.request.cart.CartDetailReq;
import com.nanhng.FastFood.entity.cart.Cart;

public interface CartService {
    Cart addCart(AddCartReq request);

    void addCart(Integer userId);

    Cart getCart(CartDetailReq request);

    Cart getCart(Integer userId);

    Cart getCart();
}

package com.nanhng.FastFood.service.repository.cart;

import com.nanhng.FastFood.entity.cart.Cart;
import com.nanhng.FastFood.entity.cart.QCart;
import com.nanhng.FastFood.entity.cart.QCartItem;

public class CartRepositoryCustomImpl implements CartRepositoryCustom {
    QCart qCart = QCart.cart;
    QCartItem qCartItem = QCartItem.cartItem;

    @Override
    public Cart getCart() {
        return null;
    }
}

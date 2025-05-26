package com.nanhng.FastFood.service.repository.cartItem;

import com.nanhng.FastFood.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem getCartItemsById(int id);

    List<CartItem> findAllByCartId(int cartId);

    boolean existsCartItemByProductId(Integer productId);
}

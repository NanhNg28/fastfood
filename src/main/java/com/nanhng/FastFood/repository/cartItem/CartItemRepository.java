package com.nanhng.FastFood.repository.cartItem;

import com.nanhng.FastFood.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>, CartItemRepositoryCustom {
    CartItem getCartItemsById(int id);

    List<CartItem> findAllByCartId(int cartId);

    boolean existsCartItemByProductId(Integer productId);

    CartItem getCartItemByProductId(Integer productId);

    CartItem findCartItemByCartIdAndProductId(Integer cartId, Integer productId);
}

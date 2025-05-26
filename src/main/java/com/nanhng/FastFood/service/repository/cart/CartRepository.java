package com.nanhng.FastFood.service.repository.cart;

import com.nanhng.FastFood.entity.cart.Cart;
import com.nanhng.FastFood.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>,CartRepositoryCustom {
    Cart findByUser(User user);

    boolean existsCartByUserId(Integer userId);

    Cart findByUserId(Integer userId);
}

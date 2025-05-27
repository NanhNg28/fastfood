package com.nanhng.FastFood.repository.order;

import com.nanhng.FastFood.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>,OrderRepositoryCustom {
}

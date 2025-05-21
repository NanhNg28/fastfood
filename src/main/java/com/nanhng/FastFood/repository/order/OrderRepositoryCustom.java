package com.nanhng.FastFood.repository.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.entity.order.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderRevenueRes> calculateRevenueByMonth();
    List<OrderCountRes> countNumberOrderByMonth();
    List<Order> getAllPending();
    List<Order> getOrderList(int page, OrderStatus status);
}

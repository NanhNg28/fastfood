package com.nanhng.FastFood.repository.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.order.OrderDetailRes;
import com.nanhng.FastFood.dto.response.order.OrderListRes;
import com.nanhng.FastFood.entity.order.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderRevenueRes> calculateRevenueByMonth();
    List<OrderCountRes> countNumberOrderByMonth();
    List<Order> getAllPending();
    List<OrderListRes> getOrderList(int page, OrderStatus status);
    long countOrder(OrderStatus status);
    long countMyOrder (OrderStatus status, Integer userId);
    List<OrderListRes> getMyOrderList (int page, OrderStatus status, Integer userId);
    OrderDetailRes getDetail(Integer id);
}

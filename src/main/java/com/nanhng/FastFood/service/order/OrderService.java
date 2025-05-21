package com.nanhng.FastFood.service.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.request.order.AddOrderReq;
import com.nanhng.FastFood.dto.request.order.UpdateOrderReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.order.AddOrderRes;
import com.nanhng.FastFood.entity.order.Order;

import java.util.List;

public interface OrderService {
    AddOrderRes addOrder(AddOrderReq request);
    Order updateOrder(UpdateOrderReq request);
    List<Order> getPendingList();
    BaseResponse<List<Order>> getOrderList(int page, OrderStatus status);
}

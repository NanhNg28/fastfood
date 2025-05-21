package com.nanhng.FastFood.repository.orderItem;

import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.dashboard.ProductRevenueRes;

import java.util.List;

public interface OrderItemRepositoryCustom {
    List<ProductRevenueRes> calculateRevenueByMonth();
}

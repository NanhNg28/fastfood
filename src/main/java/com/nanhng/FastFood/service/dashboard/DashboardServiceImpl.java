package com.nanhng.FastFood.service.dashboard;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.dashboard.ProductRevenueRes;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.order.OrderRepository;
import com.nanhng.FastFood.repository.orderItem.OrderItemRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl extends BaseService implements DashboardService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderRevenueRes> calculateTotalRevenueByMonth() {
        User user = getUser(RoleType.ADMIN);

        return orderRepository.calculateRevenueByMonth();
    }

    @Override
    public List<ProductRevenueRes> calculateProductsRevenueByMonth() {
        User user = getUser(RoleType.ADMIN);

        return orderItemRepository.calculateRevenueByMonth();
    }

    @Override
    public List<OrderCountRes> countOrderByMonth() {
        User user = getUser(RoleType.ADMIN);

        return orderRepository.countNumberOrderByMonth();
    }
}

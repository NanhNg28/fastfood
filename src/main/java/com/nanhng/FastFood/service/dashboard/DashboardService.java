package com.nanhng.FastFood.service.dashboard;

import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.dashboard.ProductRevenueRes;

import java.io.InputStream;
import java.util.List;

public interface DashboardService {
    List<OrderRevenueRes> calculateTotalRevenueByMonth();
    List<ProductRevenueRes> calculateProductsRevenueByMonth();
    List<OrderCountRes> countOrderByMonth();
    InputStream excelTotalRevenueByMonth();
}

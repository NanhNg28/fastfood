package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.dashboard.ProductRevenueRes;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.service.dashboard.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "get data of revenue each month")
    @GetMapping("v1/dashboard/total-revenue")
    public ResponseEntity<BaseResponse<List<OrderRevenueRes>>> getTotalRevenue() {
        return ResponseEntity.ok(new BaseResponse<>(dashboardService.calculateTotalRevenueByMonth(),"find total revenue successfully"));
    }

    @Operation(summary = "get data of product revenue each month")
    @GetMapping("v1/dashboard/product-revenue")
    public ResponseEntity<BaseResponse<List<ProductRevenueRes>>> getProductRevenue() {
        return ResponseEntity.ok(new BaseResponse<>(dashboardService.calculateProductsRevenueByMonth(),"find total revenue of product successfully"));
    }

    @Operation(summary = "get data of revenue each month")
    @GetMapping("v1/dashboard/product-count")
    public ResponseEntity<BaseResponse<List<OrderCountRes>>> countNumberOfOrder(){
        return ResponseEntity.ok(new BaseResponse<>(dashboardService.countOrderByMonth(),"count number of orders each month"));
    }

    @Operation(summary = "export data of revenue each month as excel")
    @GetMapping("v1/dashboard/export-total-revenue-by-month")
    public ResponseEntity<InputStreamResource> exportTotalRevenueByMonth() {
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(dashboardService.excelTotalRevenueByMonth()));
    }

    @Operation(summary = "export data of revenue each month as excel")
    @GetMapping("v1/dashboard/export-product-revenue-by-month")
    public ResponseEntity<InputStreamResource> exportProductRevenue() {
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(dashboardService.excelProductByMonth()));
    }

}

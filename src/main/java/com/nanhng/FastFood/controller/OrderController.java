package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.request.order.AddOrderReq;
import com.nanhng.FastFood.dto.request.order.UpdateOrderReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.order.AddOrderRes;
import com.nanhng.FastFood.dto.response.order.OrderDetailRes;
import com.nanhng.FastFood.dto.response.order.OrderListRes;
import com.nanhng.FastFood.entity.order.Order;
import com.nanhng.FastFood.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "new order")//done
    @PostMapping("v1/order/add")
    public ResponseEntity<AddOrderRes> addOrder(@RequestBody AddOrderReq order) {
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    @Operation(summary = "admin update order")
    @PostMapping("v1/order/update")
    public ResponseEntity<Order> updateOrder(@RequestBody UpdateOrderReq request) {
        return ResponseEntity.ok(orderService.updateOrder(request));
    }

    @Operation(summary = "admin get list pending order")//done
    @GetMapping("v1/order/list")
    public ResponseEntity<BaseResponse<List<OrderListRes>>> getListOrder(@RequestParam(required = false) OrderStatus status,
                                                                                @RequestParam int page) {
        return ResponseEntity.ok(orderService.getOrderList(page,status));
    }

    @Operation(summary = "get my order list")//done
    @GetMapping("v1/order/my-list")
    public ResponseEntity<BaseResponse<List<OrderListRes>>> getMyListOrder(@RequestParam(required = false) OrderStatus status,
                                                                                @RequestParam int page) {
        return ResponseEntity.ok(orderService.getMyOrderList(page,status));
    }

    @Operation(summary = "get detail")//done
    @GetMapping("v1/order/detail/{id}")
    public ResponseEntity<BaseResponse<OrderDetailRes>> getOrderDetail(@PathVariable int id) {
        return ResponseEntity.ok(new BaseResponse<>(orderService.getOrderDetail(id), "label_success"));
    }
}

package com.nanhng.FastFood.service.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.order.AddOrderReq;
import com.nanhng.FastFood.dto.request.order.UpdateOrderReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.order.AddOrderRes;
import com.nanhng.FastFood.dto.response.order.OrderDetailRes;
import com.nanhng.FastFood.dto.response.order.OrderListRes;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.cart.Cart;
import com.nanhng.FastFood.entity.cart.CartItem;
import com.nanhng.FastFood.entity.order.Order;
import com.nanhng.FastFood.entity.order.OrderItem;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.address.AddressRepository;
import com.nanhng.FastFood.repository.order.OrderRepository;
import com.nanhng.FastFood.repository.orderItem.OrderItemRepository;
import com.nanhng.FastFood.service.BaseService;
import com.nanhng.FastFood.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseService implements OrderService{
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public AddOrderRes addOrder(AddOrderReq request) {
        User user = getUser();

        if(request.getUserId()!= user.getId()) {
            throw new LovelyException("Wrong account", HttpStatus.UNAUTHORIZED);
        }
        if(user.getAddressId()==null){
            throw new LovelyException("User does not have exist address yet");
        }
        Double total = 0.0;
        Cart cart = cartService.getCart();
        Order order = new Order();
        Address address = addressRepository.findById(user.getAddressId()).orElseThrow(()->new LovelyException("User does not have exist address yet"));
        order.setUserId(user.getId());
        order.setStreet(address.getStreet());
        order.setCity(address.getCity());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(total);
        orderRepository.save(order);
        for(CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = setOrderItem(cartItem);
            orderItem.setOrderId(order.getId());
            total += cartItem.getPrice();
            orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(total);
        orderRepository.save(order);
        AddOrderRes addOrderRes = toAddOrderRes(order);
        eventPublisher.publishEvent(addOrderRes);
        return addOrderRes;
    }

    @Override
    public Order updateOrder(UpdateOrderReq request) {
        User user = getUser(RoleType.ADMIN,RoleType.EMPLOYEE);

        if(request.getUserId()!= user.getId()) {
            throw new LovelyException("invalid account", HttpStatus.UNAUTHORIZED);
        }

        Order order = orderRepository.findById(request.getOrderId()).orElse(null);
        if(order == null) {
            throw new LovelyException("Order not found", HttpStatus.NOT_FOUND);
        }
        if(request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
        if(request.getCity()!= null) {
            order.setCity(request.getCity());
        }
        if(request.getStreet() != null) {
            order.setStreet(request.getStreet());
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getPendingList() {
        User user = getUser(RoleType.ADMIN,RoleType.EMPLOYEE);

        return orderRepository.getAllPending();
    }

    @Override
    public BaseResponse<List<OrderListRes>> getOrderList(int page, OrderStatus status) {
        User user = getUser(RoleType.ADMIN,RoleType.EMPLOYEE);

        long count = orderRepository.countOrder(status);
        List<OrderListRes> list = orderRepository.getOrderList(page,status);

        return new BaseResponse<>(list,count,page);
    }

    @Override
    public BaseResponse<List<OrderListRes>> getMyOrderList(int page, OrderStatus status) {
        User user = getUser(RoleType.ADMIN,RoleType.CUSTOMER);
        long count = orderRepository.countMyOrder(status,user.getId());
        List<OrderListRes> list = orderRepository.getMyOrderList(page,status,user.getId());
        return new BaseResponse<>(list,count,page);
    }

    @Override
    public OrderDetailRes getOrderDetail(Integer id) {
        User user = getUser(RoleType.ADMIN,RoleType.CUSTOMER);
        OrderDetailRes response = orderRepository.getDetail(id);
        return response;
    }

    private OrderItem setOrderItem(CartItem cartItem) {
        return OrderItem.builder()
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .productId(cartItem.getProductId())
                .build();
    }

    private AddOrderRes toAddOrderRes(Order order) {
        return AddOrderRes.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .userId(order.getUserId())
                .status(order.getStatus())
                .city(order.getCity())
                .street(order.getStreet())
                .build();
    }
}

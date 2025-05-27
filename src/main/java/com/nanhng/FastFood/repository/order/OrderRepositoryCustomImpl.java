package com.nanhng.FastFood.repository.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.dto.response.order.OrderDetailRes;
import com.nanhng.FastFood.dto.response.order.OrderListRes;
import com.nanhng.FastFood.entity.order.Order;
import com.nanhng.FastFood.entity.order.OrderItem;
import com.nanhng.FastFood.entity.order.QOrder;
import com.nanhng.FastFood.entity.order.QOrderItem;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

@Slf4j
public class OrderRepositoryCustomImpl extends BaseRepository implements OrderRepositoryCustom {

    QOrder qOrder = QOrder.order;
    QOrderItem qOrderItem = QOrderItem.orderItem;

    ///////////// DASHBOARD ////////////////////////

    @Override
    public List<OrderRevenueRes> calculateRevenueByMonth() {
        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qOrder.status.eq(OrderStatus.SHIPPED));

        return query().from(qOrder)
                .where(builder)
                .select(Projections.fields(OrderRevenueRes.class,
                        qOrder.totalPrice.sum().as("revenue"),
                        qOrder.createdAt.yearMonth().as("date")))
                .groupBy(qOrder.createdAt.yearMonth())
                .fetch();
    }

    @Override
    public List<OrderCountRes> countNumberOrderByMonth() {
        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qOrder.status.eq(OrderStatus.SHIPPED));

        return query().from(qOrder)
                .where(builder)
                .select(Projections.fields(OrderCountRes.class,
                        qOrder.createdAt.yearMonth().as("date"),
                        qOrder.id.count().as("orderNum")))
                .groupBy(qOrder.createdAt.yearMonth())
                .fetch();
    }

    // /////////////////////////////////////////////


    @Override
    public List<Order> getAllPending() {
        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qOrder.status.eq(OrderStatus.PENDING));

        return query().from(qOrder)
                .select(qOrder)
                .fetch();
    }

    @Override
    public List<OrderListRes> getOrderList(int page, OrderStatus status) {
        BooleanBuilder builder = new BooleanBuilder();
        if (status != null) {
            builder.and(qOrder.status.eq(status));
        }
        builder.and(qOrder.deleted.eq(false));

        List<OrderListRes> list = query().from(qOrder).leftJoin(qOrderItem).on(qOrder.id.eq(qOrderItem.orderId))
                .where(builder)
                .select(Projections.fields(OrderListRes.class,
                        qOrder.id,
                        qOrder.userId,
                        qOrder.status,
                        qOrder.street,
                        qOrder.createdAt,
                        qOrder.updatedAt
                ))
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();

        Map<Integer, OrderListRes> orderMap = list.stream()
                .collect(Collectors.toMap(OrderListRes::getId, o -> o));

        List<OrderItem> items = query().from(qOrderItem)
                .where(qOrderItem.orderId.in(orderMap.keySet()))
                .select(qOrderItem)
                .fetch();

        for (OrderItem item : items) {
            OrderListRes order = orderMap.get(item.getOrderId());
            if (order.getOrderItems() == null) {
                order.setOrderItems(new ArrayList<>());
            }
            order.getOrderItems().add(item);
        }
        return list;
    }

    @Override
    public long countOrder(OrderStatus status) {
        BooleanBuilder builder = new BooleanBuilder();
        if (status != null) {
            builder.and(qOrder.status.eq(status));
        }
        builder.and(qOrder.deleted.eq(false));

        Long count = query().from(qOrder).leftJoin(qOrderItem).on(qOrder.id.eq(qOrderItem.orderId))
                .where(builder)
                .select(qOrder.id.count())
                .fetchOne();
        return count==null?0:count;
    }

    @Override
    public long countMyOrder(OrderStatus status, Integer userId) {
        BooleanBuilder builder = new BooleanBuilder();
        if (status != null) {
            builder.and(qOrder.status.eq(status));
        }
        builder.and(qOrder.deleted.eq(false));
        builder.and(qOrder.userId.eq(userId));

        Long count = query().from(qOrder).leftJoin(qOrderItem).on(qOrder.id.eq(qOrderItem.orderId))
                .where(builder)
                .select(qOrder.id.count())
                .fetchOne();
        return count==null?0:count;
    }

    @Override
    public List<OrderListRes> getMyOrderList(int page, OrderStatus status, Integer userId) {
        BooleanBuilder builder = new BooleanBuilder();
        if (status != null) {
            builder.and(qOrder.status.eq(status));
        }
        builder.and(qOrder.deleted.eq(false));
        builder.and(qOrder.userId.eq(userId));

        List<OrderListRes> list = query().from(qOrder).leftJoin(qOrderItem).on(qOrder.id.eq(qOrderItem.orderId))
                .where(builder)
                .select(Projections.fields(OrderListRes.class,
                        qOrder.id,
                        qOrder.userId,
                        qOrder.status,
                        qOrder.street,
                        qOrder.createdAt,
                        qOrder.updatedAt
                ))
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();

        Map<Integer, OrderListRes> orderMap = list.stream()
                .collect(Collectors.toMap(OrderListRes::getId, o -> o));

        List<OrderItem> items = query().from(qOrderItem)
                .where(qOrderItem.orderId.in(orderMap.keySet()))
                .select(qOrderItem)
                .fetch();

        for (OrderItem item : items) {
            OrderListRes order = orderMap.get(item.getOrderId());
            if (order.getOrderItems() == null) {
                order.setOrderItems(new ArrayList<>());
            }
            order.getOrderItems().add(item);
        }
        return list;
    }

    @Override
    public OrderDetailRes getDetail(Integer id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qOrder.deleted.eq(false));
        builder.and(qOrder.id.eq(id));

        OrderDetailRes res = query().from(qOrder)
                .where(builder)
                .select(Projections.fields(OrderDetailRes.class,
                        qOrder.id,
                        qOrder.userId,
                        qOrder.status,
                        qOrder.street,
                        qOrder.createdAt,
                        qOrder.updatedAt,
                        qOrder.totalPrice
                ))
                .fetchOne();
        List<OrderItem> items = query().from(qOrderItem)
                .where(qOrderItem.orderId.eq(id))
                .select(qOrderItem)
                .fetch();
        if(res!=null){
            res.setOrderItems(items);
        }
        return res;
    }
}

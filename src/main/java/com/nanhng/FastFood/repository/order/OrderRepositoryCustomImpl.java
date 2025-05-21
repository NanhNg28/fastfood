package com.nanhng.FastFood.repository.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.dto.response.dashboard.OrderCountRes;
import com.nanhng.FastFood.dto.response.dashboard.OrderRevenueRes;
import com.nanhng.FastFood.entity.order.Order;
import com.nanhng.FastFood.entity.order.QOrder;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

@Slf4j
public class OrderRepositoryCustomImpl extends BaseRepository implements OrderRepositoryCustom {

    QOrder qOrder = QOrder.order;

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
    public List<Order> getOrderList(int page, OrderStatus status) {
        BooleanBuilder builder = new BooleanBuilder();
        if(status!=null){
            builder.and(qOrder.status.eq(status));
        }
        builder.and(qOrder.deleted.eq(false));

        return query().from(qOrder)
                .where(builder)
                .select(qOrder)
                .offset(page*PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();
    }
}

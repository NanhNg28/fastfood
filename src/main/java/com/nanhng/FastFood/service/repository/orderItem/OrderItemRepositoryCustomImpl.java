package com.nanhng.FastFood.service.repository.orderItem;

import com.nanhng.FastFood.dto.response.dashboard.ProductRevenueRes;
import com.nanhng.FastFood.entity.order.QOrder;
import com.nanhng.FastFood.entity.order.QOrderItem;
import com.nanhng.FastFood.entity.product.QProduct;
import com.nanhng.FastFood.service.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;

import java.util.List;

public class OrderItemRepositoryCustomImpl extends BaseRepository implements OrderItemRepositoryCustom {

    QOrderItem qOrderItem = QOrderItem.orderItem;
    QOrder qOrder = QOrder.order;
    QProduct qProduct = QProduct.product;

    @Override
    public List<ProductRevenueRes> calculateRevenueByMonth() {
        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qOrder.status.eq(OrderStatus.SHIPPED));
        builder.and(qOrderItem.deleted.eq(false));

        return query().from(qOrderItem).innerJoin(qOrder).on(qOrder.id.eq(qOrderItem.orderId))
                .innerJoin(qProduct).on(qOrderItem.productId.eq(qProduct.id))
                .where(builder)
                .select(Projections.fields(ProductRevenueRes.class,
                        qProduct.id.as("productId"),
                        qProduct.name.as("productName"),
                        qOrderItem.price.sum().as("revenue"),
                        qOrder.createdAt.yearMonth().as("date")))
                .groupBy(qOrderItem.productId)
                .groupBy(qOrder.createdAt.yearMonth())
                .fetch();
    }
}

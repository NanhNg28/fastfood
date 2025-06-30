package com.nanhng.FastFood.entity.order;

import com.nanhng.FastFood.entity.order.constants.OrderStatus;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.order.constants.PaymentGateway;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    String note;

    @Transient
    List<OrderItem> orderItems;

    @NotNull
    @Column(name = "user_id")
    Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",columnDefinition = "VARCHAR(50)")
    OrderStatus status;

    @Transient
    Address address;

    @Column(name = "payment_gateway", columnDefinition = "VARCHAR(50)")
    PaymentGateway paymentGateway = PaymentGateway.MANUAL;
}

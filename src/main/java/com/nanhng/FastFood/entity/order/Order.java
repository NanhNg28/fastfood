package com.nanhng.FastFood.entity.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Transient
    List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "total_price")
    Double totalPrice = 0.0;

    @NotNull
    @Column(name = "user_id")
    Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",columnDefinition = "VARCHAR(50)")
    OrderStatus status;

    @NotNull
    String city;
    @NotNull
    String street;
}

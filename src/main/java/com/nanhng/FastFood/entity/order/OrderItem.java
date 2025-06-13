package com.nanhng.FastFood.entity.order;

import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    private Integer quantity;
    private Integer price;

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "product_id")
    Integer productId;
}

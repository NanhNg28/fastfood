package com.nanhng.FastFood.entity.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.entity.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Integer quantity;

    Double price;

    @Column(name = "cart_id")
    Integer cartId;

    @Column(name = "product_id")
    Integer productId;

}

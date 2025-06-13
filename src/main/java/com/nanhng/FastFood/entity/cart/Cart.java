package com.nanhng.FastFood.entity.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.PaymentStatus;
import com.nanhng.FastFood.entity.BaseEntity;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    Integer userId;

    @JsonIgnore
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne
    User user;

    @Transient
    private List<CartItem> cartItems = new ArrayList<>();

}

package com.nanhng.FastFood.entity.transaction;

import com.nanhng.FastFood.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction extends BaseEntity {
    @Column(name = "user_id")
    Integer userId;

    Integer amount;
    String content;
}

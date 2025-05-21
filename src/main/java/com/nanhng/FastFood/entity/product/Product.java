package com.nanhng.FastFood.entity.product;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.category.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @NotNull
    String name;

    @NotNull
    Double price;

    @Column(name = "category_id")
    Integer categoryId;

    int quantity;
    String shortDescription;
    String longDescription;

    @Column(name = "status", columnDefinition = "INT")
    ActiveStatus status;

    boolean deleted;

    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    Category category;


    String imagePath;
}

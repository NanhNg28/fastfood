package com.nanhng.FastFood.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.category.Category;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
public class Product extends BaseEntity {
    @NotNull
    String name;

    @NotNull
    Integer price;

    @Column(name = "category_id")
    Integer categoryId;

    int quantity;
    String shortDescription;
    String longDescription;

    @JsonIgnore
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    Category category;

    @Column(name = "image_id")
    Integer imageId;

    @Transient
    UploadFile image;

    @Column(name = "discount_percentage")
    Integer discountPercentage;

    @Column(name = "discount_expiry_date")
    LocalDate discountExpiryDate;
}

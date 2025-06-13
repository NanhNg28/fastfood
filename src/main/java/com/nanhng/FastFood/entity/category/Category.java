package com.nanhng.FastFood.entity.category;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "categories")
public class Category extends BaseEntity {

    @NotNull
    String name;
    String description;
    @Column(name = "image_id")
    Integer imageId;

    boolean deleted;
}

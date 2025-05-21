package com.nanhng.FastFood.dto.response.product;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductImageRes {
    Integer productId;
    String name;
    Integer imageId;
    String imagePath;
}

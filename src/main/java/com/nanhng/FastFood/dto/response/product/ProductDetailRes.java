package com.nanhng.FastFood.dto.response.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailRes {
    String name;
    Double price;
    String categoryName;

    int quantity;
    String shortDescription;
    String longDescription;
}

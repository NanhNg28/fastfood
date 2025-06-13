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
    Integer id;
    String name;
    Integer price;
    String categoryName;

    int quantity;
    String shortDescription;
    String longDescription;

    Integer imageId;
    String thumbUrl;
    String thumbName;

    Integer categoryId;
}

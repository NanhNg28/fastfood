package com.nanhng.FastFood.dto.response.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRes {
    String name;
    Double price;
    String categoryName;

}

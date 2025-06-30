package com.nanhng.FastFood.dto.response.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListRes {
    Integer id;
    String name;
    Double price;
    String categoryName;
    String thumbUrl;
    String thumbName;
    String shortDescription;
    Integer categoryId;
    Integer discountPercentage;
    LocalDate discountExpiryDate;
}

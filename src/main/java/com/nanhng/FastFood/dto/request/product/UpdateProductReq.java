package com.nanhng.FastFood.dto.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductReq {
    @NotNull
    Integer id;

    String name;

    Double price;

    Integer categoryId;

    Integer quantity;

    String shortDescription;
    String longDescription;
    Integer imageId;

}

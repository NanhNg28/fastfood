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
public class AddProductReq {
    @NotNull
    String name;

    @NotNull
    Double price;

    @NotNull
    Integer categoryId;

    @NotNull
    Integer quantity;

    String shortDescription;
    String longDescription;
    Integer imageId;


}

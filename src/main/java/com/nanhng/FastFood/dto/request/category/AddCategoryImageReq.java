package com.nanhng.FastFood.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCategoryImageReq {
    @NotNull
    Integer categoryId;

    @NotNull
    Integer imageId;
}

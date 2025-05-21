package com.nanhng.FastFood.dto.request.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCategoryReq extends AddCategoryReq{
    Integer id;
}

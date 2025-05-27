package com.nanhng.FastFood.dto.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddOrderReq {
    @NotNull
    Integer userId;
    @NotNull
    String name;
    String note;
}

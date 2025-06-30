package com.nanhng.FastFood.dto.request.cartItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCartItemReq {
    @NotNull
    Integer cartItemId;
    @NotNull
    Integer quantity;
}

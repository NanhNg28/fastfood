package com.nanhng.FastFood.dto.request.cartItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCartItemReq {
    @NotNull(message = "số lượng không được để trống")
    Integer quantity;
    @NotNull(message ="id sản phẩm không được để trống")
    Integer productId;

}

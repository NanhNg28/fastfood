package com.nanhng.FastFood.dto.request.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddAddressReq {
    @NotNull
    String city;
    @NotNull
    String street;
}

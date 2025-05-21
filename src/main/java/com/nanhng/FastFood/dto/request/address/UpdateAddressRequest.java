package com.nanhng.FastFood.dto.request.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAddressRequest {

    @NotNull
    Integer id;
    String city;
    String street;
    ActiveStatus status;
}

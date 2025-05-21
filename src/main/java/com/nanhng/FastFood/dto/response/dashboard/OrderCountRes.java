package com.nanhng.FastFood.dto.response.dashboard;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCountRes {
    Integer date;
    Long orderNum;
}

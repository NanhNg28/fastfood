package com.nanhng.FastFood.dto.response.dashboard;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRevenueRes {
    Integer productId;
    String productName;
    Double revenue =0.0;
    Integer date;
}

package com.nanhng.FastFood.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetailRes {
    Integer id;
    String name;
    String description;
    String originUrl;
    String originName;
}

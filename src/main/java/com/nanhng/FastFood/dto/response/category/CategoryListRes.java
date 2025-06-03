package com.nanhng.FastFood.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListRes {
    Integer id;
    String name;
    String description;
    String thumbUrl;
    String thumbName;
    Integer imageId;
}

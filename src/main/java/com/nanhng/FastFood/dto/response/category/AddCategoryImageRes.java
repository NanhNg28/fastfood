package com.nanhng.FastFood.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryImageRes {
    Integer categoryId;
    String name;
    Integer imageId;
    String imagePath;
}

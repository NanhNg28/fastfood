package com.nanhng.FastFood.dto.response.blog;

import com.nanhng.FastFood.entity.upload_file.UploadFile;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListBlogRes {
    String title;
    Integer authorId;
    UploadFile thumbnail;
    String introduction;
}

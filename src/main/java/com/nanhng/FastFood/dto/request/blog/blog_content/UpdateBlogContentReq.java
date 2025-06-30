package com.nanhng.FastFood.dto.request.blog.blog_content;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBlogContentReq {
    @NotNull
    Integer blogContentId;
    String content;
    Integer uploadFileId;
}

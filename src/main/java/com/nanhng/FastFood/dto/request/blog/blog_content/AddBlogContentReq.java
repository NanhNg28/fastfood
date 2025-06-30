package com.nanhng.FastFood.dto.request.blog.blog_content;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBlogContentReq {
    @NotNull
    Integer blogId;
    String content;
    Integer uploadFileId;
}

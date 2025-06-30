package com.nanhng.FastFood.dto.request.blog.blog;

import lombok.Data;

@Data
public class UpdateBlogReq {
    private Integer blogId;

    private String title;
    private String introduction;
    private Integer thumbnailImageId;

}

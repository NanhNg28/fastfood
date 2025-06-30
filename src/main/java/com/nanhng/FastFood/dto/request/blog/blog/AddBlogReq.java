package com.nanhng.FastFood.dto.request.blog.blog;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AddBlogReq {
    @NotNull
    private String title;
    @NotNull
    private String introduction;
    @NotNull
    private Integer thumbnailImageId;

    private List<BlogContentReq> listContents;

    @Getter
    @Setter
    public static class BlogContentReq {
        private String content;
        private Integer uploadFileId;
    }
}

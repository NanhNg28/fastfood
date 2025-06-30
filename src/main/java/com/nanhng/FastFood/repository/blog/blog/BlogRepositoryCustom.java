package com.nanhng.FastFood.repository.blog.blog;

import com.nanhng.FastFood.dto.response.blog.ListBlogRes;

import java.util.List;

public interface BlogRepositoryCustom {
    long countBlog(String keyword);
    List<ListBlogRes> getListBlog (int page, String keyword);
    List<Integer> getExistIds(List<Integer> ids);
    void deleteByIds(List<Integer> ids);
}

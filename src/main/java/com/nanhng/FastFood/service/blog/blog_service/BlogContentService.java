package com.nanhng.FastFood.service.blog.blog_service;

import com.nanhng.FastFood.dto.request.blog.blog_content.AddBlogContentReq;
import com.nanhng.FastFood.dto.request.blog.blog_content.UpdateBlogContentReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.entity.blog.BlogContent;

import java.util.List;

public interface BlogContentService {
    BlogContent add(AddBlogContentReq request);
    BlogContent update(UpdateBlogContentReq request);
    List<Integer> delete(IdsRequest request);
}

package com.nanhng.FastFood.service.blog.blog;

import com.nanhng.FastFood.dto.request.blog.blog.AddBlogReq;
import com.nanhng.FastFood.dto.request.blog.blog.UpdateBlogReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.blog.ListBlogRes;
import com.nanhng.FastFood.entity.blog.Blog;

import java.util.List;

public interface BlogService {
    Blog addBlog(AddBlogReq request);
    Blog getBlogDetail(Integer id);
    BaseResponse<List<ListBlogRes>> getBlogList(Integer page, String keyword);
    List<Integer> deleteBlog(IdsRequest request);
    Blog updateBlog(UpdateBlogReq request);
}

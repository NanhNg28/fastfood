package com.nanhng.FastFood.service.blog.blog_service;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.blog.blog_content.AddBlogContentReq;
import com.nanhng.FastFood.dto.request.blog.blog_content.UpdateBlogContentReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.entity.blog.Blog;
import com.nanhng.FastFood.entity.blog.BlogContent;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.blog.blog_content.BlogContentRepository;
import com.nanhng.FastFood.repository.blog.blog.BlogRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogContentServiceImpl extends BaseService implements BlogContentService {
    private final BlogContentRepository blogContentRepository;
    private final BlogRepository blogRepository;

    @Override
    public BlogContent add(AddBlogContentReq request) {
        User user = getUser(RoleType.ADMIN);

        Blog blog = blogRepository.findById(request.getBlogId()).orElse(null);
        if(blog==null || blog.getAuthorId()!=user.getId()){
            throw new LovelyException("error", HttpStatus.BAD_REQUEST);
        }

        BlogContent blogContent = new BlogContent();
        blogContent.setBlogId(request.getBlogId());
        blogContent.setContent(request.getContent());
        blogContent.setUploadFileId(request.getUploadFileId());
        return blogContentRepository.save(blogContent);
    }

    @Override
    public BlogContent update(UpdateBlogContentReq request) {
        User user = getUser(RoleType.ADMIN);

        BlogContent blogContent = blogContentRepository.findByIdToUpdate(request.getBlogContentId());
        if(blogContent==null){
            throw new LovelyException("Không tìm thấy blog content", HttpStatus.BAD_REQUEST);
        }
        Blog blog = blogRepository.findById(blogContent.getBlogId()).orElse(null);
        if(blog==null || blog.getAuthorId()!=user.getId()){
            throw new LovelyException("error", HttpStatus.BAD_REQUEST);
        }
        if(request.getContent()!=null){
            blogContent.setContent(request.getContent());
        }
        if(request.getUploadFileId()!=null){
            blogContent.setUploadFileId(request.getUploadFileId());
        }
        return blogContentRepository.save(blogContent);
    }

    @Override
    public List<Integer> delete(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        blogContentRepository.deleteAllByIdInBatch(request.getIds());
        return request.getIds();
    }
}

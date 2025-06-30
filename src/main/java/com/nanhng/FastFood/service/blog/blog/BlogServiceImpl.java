package com.nanhng.FastFood.service.blog.blog;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.blog.blog.AddBlogReq;
import com.nanhng.FastFood.dto.request.blog.blog.UpdateBlogReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.blog.ListBlogRes;
import com.nanhng.FastFood.entity.blog.Blog;
import com.nanhng.FastFood.entity.blog.BlogContent;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.blog.blog_content.BlogContentRepository;
import com.nanhng.FastFood.repository.blog.blog.BlogRepository;
import com.nanhng.FastFood.repository.upload_file.UploadFileRepository;
import com.nanhng.FastFood.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl extends BaseService implements BlogService {
    private final UploadFileRepository uploadFileRepository;
    private final BlogRepository blogRepository;
    private final BlogContentRepository blogContentRepository;

    @Override
    @Transactional
    public Blog addBlog(AddBlogReq request) {
        User user = getUser(RoleType.ADMIN);
        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setAuthorId(user.getId());
        UploadFile file = uploadFileRepository.findById(request.getThumbnailImageId()).orElse(null);
        blog.setThumbnailImageId(request.getThumbnailImageId());
        blog.setThumbnail(file);
        blog.setIntroduction(request.getIntroduction());
        blogRepository.save(blog);

        List<Integer> fileIds = request.getListContents()
                .stream()
                .filter(Objects::nonNull)
                .map(AddBlogReq.BlogContentReq::getUploadFileId)
                .toList();

        Map<Integer, UploadFile> tempMap = uploadFileRepository.findAllById(fileIds)
                .stream()
                .collect(Collectors.toMap(UploadFile::getId, t-> t));

        List<BlogContent> listBlogContentsToSave = new LinkedList<>();
        for (AddBlogReq.BlogContentReq data : request.getListContents()) {
            BlogContent blogContent = new BlogContent();
            blogContent.setBlogId(blog.getId());
            blogContent.setContent(data.getContent());
            blogContent.setUploadFileId(data.getUploadFileId());
            blogContent.setUploadFile(tempMap.get(data.getUploadFileId()));
            listBlogContentsToSave.add(blogContent);
        }
        blogContentRepository.saveAll(listBlogContentsToSave);
        blog.setListContents(listBlogContentsToSave);
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlogDetail(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new LovelyException("blog not found", HttpStatus.NOT_FOUND));
        if (blog.getThumbnailImageId() != null) {
            blog.setThumbnail(uploadFileRepository.findById(blog.getThumbnailImageId()).orElse(null));
        }
        List<BlogContent> contents = blogContentRepository.findByBlogId(blog.getId());
        List<Integer> fileIds = contents.stream()
                .map(BlogContent::getUploadFileId)
                .filter(Objects::nonNull)
                .toList();

        Map<Integer, UploadFile> fileMap = uploadFileRepository.findAllById(fileIds)
                .stream().collect(Collectors.toUnmodifiableMap(UploadFile::getId, f -> f));
        for (BlogContent content : contents) {
            content.setUploadFile(fileMap.get(content.getUploadFileId()));
        }
        blog.setListContents(contents);
        return blog;
    }

    @Override
    public BaseResponse<List<ListBlogRes>> getBlogList(Integer page, String keyword) {
        long count = blogRepository.countBlog(keyword);
        List<ListBlogRes> listBlogRes = blogRepository.getListBlog(page,keyword);

        return new BaseResponse<>(listBlogRes, count, page);
    }

    @Override
    public List<Integer> deleteBlog(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        List<Integer> ids = request.getIds();
        List<Integer> existIds = blogRepository.getExistIds(ids);
        List<Integer> notExistId = ids.stream().filter(id -> !existIds.contains(id)).toList();
        if(!notExistId.isEmpty()) {
            throw new LovelyException("Không tìm thấy id",HttpStatus.NOT_FOUND);
        }
        blogRepository.deleteByIds(ids);
        return existIds;
    }

    @Override
    public Blog updateBlog(UpdateBlogReq request) {
        User user = getUser(RoleType.ADMIN);

        Blog blog = blogRepository.findById(request.getBlogId()).orElse(null);
        if(blog==null || !blog.getAuthorId().equals(user.getId())) {
            throw new LovelyException("Không tìm thấy blog", HttpStatus.BAD_REQUEST);
        }
        if(request.getTitle()!=null && !request.getTitle().isBlank()) {
            blog.setTitle(request.getTitle());
        }
        if(request.getIntroduction()!=null) {
            blog.setIntroduction(request.getIntroduction());
        }
        if(request.getThumbnailImageId()!=null) {
            blog.setThumbnailImageId(request.getThumbnailImageId());
        }
        return blogRepository.save(blog);
    }
}
